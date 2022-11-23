package com.base.saas.gateway.filter;

import com.alibaba.fastjson.JSONException;
import com.alibaba.fastjson.JSONObject;
import com.base.saas.AppConstant;
import com.base.saas.userinfo.UserContextUtil;

import com.base.saas.userinfo.UserInfo;
import com.netflix.util.Pair;
import com.netflix.zuul.ZuulFilter;
import com.netflix.zuul.context.RequestContext;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cloud.netflix.zuul.filters.support.FilterConstants;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.util.StreamUtils;
import org.springframework.web.client.RestTemplate;

import javax.servlet.http.HttpServletRequest;
import java.io.InputStream;
import java.io.UnsupportedEncodingException;
import java.net.URLDecoder;
import java.nio.charset.Charset;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * 对某些api请求的返回需要生成日志并且存储于数据库日志表中
 * <p>
 * 扩展：如果有app 的api某些功能使用到日志，那么当前需要在run中对后管端和app端分别处理自己的日志
 */
public class PostFilter extends ZuulFilter {
    @Autowired
    private RestTemplate restTemplate;

    //post：在route或error过滤器之后被调用
    @Override
    public String filterType() {
        return FilterConstants.POST_TYPE;
    }

    // 优先级为0，数字越大，优先级越低
    @Override
    public int filterOrder() {
        return FilterConstants.SEND_RESPONSE_FILTER_ORDER - 3;
    }

    // 是否执行该过滤器，此处为true，说明需要过滤
    @Override
    public boolean shouldFilter() {
        RequestContext ctx = RequestContext.getCurrentContext();

        String requestURI = String.valueOf(ctx.get("requestURI"));

        if (requestURI.contains("swagger") || requestURI.contains("api-docs")) {
            //不需要处理的URL请求，返回false
            return false;
        }

        //如果不是管理端后台api，不需要下面操作
        String serviceId = String.valueOf(ctx.get("serviceId"));
        if (!serviceId.equals(AppConstant.MANAGE_NAME)) {
            return false;
        }

        return true;
    }

    //过滤器的具体逻辑。
    @Override
    public Object run() {
        RequestContext context = RequestContext.getCurrentContext();

        try {
            //1.处理具体请求结果返回值
            // 获取返回值内容，加以处理
            InputStream stream = context.getResponseDataStream();
            String body = StreamUtils.copyToString(stream, Charset.forName("UTF-8")).replaceAll("(\\r\\n|\\r|\\n|\\n\\r)", "").trim();

            JSONObject jsonObject = null;
            final String[] errorMessage = {null};

            if (!"".equals(body) && body != null) {//表示当前请求接口成功

                //表示获取的是集合
                if (body.substring(0, 1).equals("[")) {

                } else {
                    jsonObject = JSONObject.parseObject(body);


                }

            } else {//表示当前请求接口失败
                List<Pair<String, String>> responseHeader = context.getZuulResponseHeaders();

                responseHeader.stream()
                        .filter(item -> item.first().equals("saas-error-message"))
                        .map(item -> item.second())
                        .forEach(item ->
                                {
                                    try {
                                        errorMessage[0] = URLDecoder.decode(item, "utf-8");
                                    } catch (UnsupportedEncodingException e) {
                                        e.printStackTrace();
                                    }
                                }
                        );

            }

            //处理日志信息
            Map sysWebLog = new HashMap<>();
            //1.创建日志对象并初始化
            sysWebLog = createLogAddInitData(context, sysWebLog, jsonObject, errorMessage[0]);
            //调用新增日志服务
            HttpHeaders requestHeaders = new HttpHeaders();
            requestHeaders.setContentType(MediaType.APPLICATION_JSON);
            HttpEntity<Map> requestEntity = new HttpEntity<Map>(sysWebLog, requestHeaders);

            ResponseEntity entity = restTemplate.postForEntity("http://" + AppConstant.MANAGE_ADDLOG_API, requestEntity, Object.class);

            //因为被消费了，所以这里需要重新赋值，否则接口返回body为空
            context.setResponseBody(body);
        } catch (JSONException e) {
            e.printStackTrace();
        } catch (Exception e) {
            e.printStackTrace();
        }

        return null;
    }

    /**
     * 如果errorMessage为空，表示成功；否则表示失败
     *
     * @param context
     * @param sysWebLog
     * @param jsonObject
     * @param errorMessage
     * @return
     * @throws Exception
     */
    private Map createLogAddInitData(RequestContext context, Map sysWebLog, JSONObject jsonObject, String errorMessage) throws Exception {
        HttpServletRequest request = context.getRequest();

        if (StringUtils.contains(request.getRequestURL().toString(), AppConstant.MANAGE_LOGIN_API_CONTAIN)) {
            //记录登录日志
            sysWebLog.put("operateType", "0");
        } else {
            //记录操作日志
            sysWebLog.put("operateType", "1");
        }
        //判断访问来源
        sysWebLog.put("terminalType", "0");
        sysWebLog.put("operateIp", request.getLocalAddr());

        String methodType = request.getMethod();
        if ("POST".equals(methodType)) {
            InputStream in = request.getInputStream();
            String body = StreamUtils.copyToString(in, Charset.forName("UTF-8"));
            sysWebLog.put("methodArgs", body);

        } else {
            sysWebLog.put("methodArgs", request.getQueryString());
        }
        sysWebLog.put("method", request.getRequestURI().substring(request.getRequestURI().lastIndexOf("/")));

        String token = request.getHeader("X-UserToken");
        UserInfo userInfo = UserContextUtil.getUserInfo(token);

        if (userInfo != null) {
            sysWebLog.put("loginAccount", userInfo.getRealName());
            sysWebLog.put("createBy", userInfo.getAccount());
            sysWebLog.put("companyCode", userInfo.getCompanyCode());
        }

        if (userInfo == null && jsonObject != null && jsonObject.getString("companyCode") != null) {
            sysWebLog.put("loginAccount", jsonObject.getString("realName"));
            sysWebLog.put("createBy", jsonObject.getString("account"));
            sysWebLog.put("companyCode", jsonObject.getString("companyCode"));
        }
        int statusCode = 400;
        if (errorMessage == null) {
            statusCode = 200;
        }
        sysWebLog.put("statusCode", statusCode + "");

        if (statusCode == 200) {
            //请求成功
            sysWebLog.put("status", "0");
            sysWebLog.put("messages", "成功");
        } else {
            sysWebLog.put("status", "1");
            sysWebLog.put("messages", "失败：" + errorMessage);

            try {
                //设置异常信息
                sysWebLog.put("exceptionCode", jsonObject.get("exceptionCode") == null ? null : jsonObject.get("exceptionCode").toString());
                sysWebLog.put("exceptionDescription", jsonObject.get("exceptionDescription") == null ? null : jsonObject.get("exceptionDescription").toString());

            } catch (Exception e) {
                e.printStackTrace();
            }

        }

        return sysWebLog;
    }

}
