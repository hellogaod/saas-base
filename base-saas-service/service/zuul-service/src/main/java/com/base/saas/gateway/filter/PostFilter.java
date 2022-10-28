package com.base.saas.gateway.filter;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONException;
import com.alibaba.fastjson.JSONObject;
import com.alibaba.fastjson.serializer.SerializerFeature;
import com.base.saas.common.constant.ServerConstans;
import com.base.saas.common.logger.EntLog;
import com.base.saas.common.response.ResponseInfo;
import com.base.saas.common.userinfo.UserContextUtil;
import com.base.saas.common.userinfo.UserInfo;
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
import javax.servlet.http.HttpServletResponse;
import java.io.InputStream;
import java.net.URLDecoder;
import java.nio.charset.Charset;
import java.util.List;

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


        return true;
    }

    //过滤器的具体逻辑。
    @Override
    public Object run() {
        RequestContext context = RequestContext.getCurrentContext();
        HttpServletRequest request = context.getRequest();
        HttpServletResponse response = context.getResponse();
        UserContextUtil.setHttpServletRequest(request);
        UserContextUtil.setHttpServletResponse(response);

        //处理日志信息
        EntLog sysWebLog = new EntLog();
        try {
            //1.处理具体请求结果返回值
            // 获取返回值内容，加以处理
            InputStream stream = context.getResponseDataStream();
            String body = StreamUtils.copyToString(stream, Charset.forName("UTF-8")).replaceAll("(\\r\\n|\\r|\\n|\\n\\r)", "").trim();
            if (!"".equals(body) && body != null) {
                if (body.substring(0, 1).equals("[")) {
                    //[{},{}]
                    JSONArray arr = JSONObject.parseArray(body);
                    context.setResponseBody(body);
                } else {
                    JSONObject jsonObject = JSONObject.parseObject(body);
                    if (jsonObject == null) {
                        context.setResponseBody(null);
                    } else {
                        context.setResponseBody(JSONObject.toJSONString(jsonObject, SerializerFeature.WriteMapNullValue));
                    }
                    //2.创建日志对象并初始化
                    sysWebLog = createLogAddInitData(context, sysWebLog, jsonObject);
                    //调用新增日志服务
                    HttpHeaders requestHeaders = new HttpHeaders();
                    requestHeaders.setContentType(MediaType.APPLICATION_JSON);
                    HttpEntity<EntLog> requestEntity = new HttpEntity<EntLog>(sysWebLog, requestHeaders);
                    ResponseEntity<ResponseInfo> entity = restTemplate.postForEntity("http://" + ServerConstans.MANAGER + "/api/weblog/addweblog", requestEntity, ResponseInfo.class);
                    if (jsonObject.containsKey("responseBody")) {
                        Object obj = jsonObject.get("responseBody");
                        if (obj == null) {
                            context.setResponseBody(null);
                        } else {
                            context.setResponseBody(JSONObject.toJSONString(obj, SerializerFeature.WriteMapNullValue));
                        }


                    } else {
                        context.setResponseBody(body);

                    }
                }

            } else {
                context.setResponseBody(body);
            }
        } catch (JSONException e) {
            e.printStackTrace();
        } catch (Exception e) {
            e.printStackTrace();
        }


        return null;
    }

    private EntLog createLogAddInitData(RequestContext context, EntLog sysWebLog, JSONObject jsonObject) throws Exception {
        HttpServletRequest request = context.getRequest();
        if (StringUtils.containsIgnoreCase(request.getRequestURL().toString(), "api/syslogin/syslogin")
                || StringUtils.contains(request.getRequestURL().toString(), "api/login/doLogin")) {
            //记录登录日志
            sysWebLog.setOperateType("0");
        } else {
            //记录操作日志
            sysWebLog.setOperateType("1");
        }
        //判断访问来源
        sysWebLog.setTerminalType(getRequestSource(request));
        sysWebLog.setOperateIp(request.getLocalAddr());
        String method = request.getMethod();
        if ("POST".equals(method)) {
            InputStream in = request.getInputStream();
            String body = StreamUtils.copyToString(in, Charset.forName("UTF-8"));
            sysWebLog.setMethodArgs(body);
        } else {
            sysWebLog.setMethodArgs(request.getQueryString());
        }
        sysWebLog.setMethod(request.getRequestURI().substring(request.getRequestURI().lastIndexOf("/")));

        Object userInfoObject = jsonObject.get("userInfo");
        UserInfo userInfo = null;
        if (userInfoObject == null) {
            String sessionId = jsonObject.get("sessionId") == null ? null : jsonObject.get("sessionId").toString();
            if (sessionId != null) {
                userInfo = UserContextUtil.getUserInfo();
            }
        } else {
            userInfo = JSONObject.toJavaObject(JSON.parseObject(jsonObject.get("userInfo").toString()), UserInfo.class);
        }
        if (userInfo != null) {
            sysWebLog.setLoginAccount(userInfo.getAccount());
            sysWebLog.setCreateBy(userInfo.getAccount());
            sysWebLog.setCompanyCode(userInfo.getCompanyCode());
            sysWebLog.setCompanyName(userInfo.getCompanyName());
        }
        int statusCode = 400;
        if (jsonObject.containsKey("statusCode")) {
            statusCode = jsonObject.getInteger("statusCode");
        }
        sysWebLog.setStatusCode(statusCode + "");
        if (statusCode == 200) {
            //请求成功
            sysWebLog.setStatus("0");
        } else {
            sysWebLog.setStatus("1");
            //设置异常信息
            sysWebLog.setExceptionCode(jsonObject.get("exceptionCode") == null ? null : jsonObject.get("exceptionCode").toString());
            sysWebLog.setExceptionDescription(jsonObject.get("exceptionDescription") == null ? null : jsonObject.get("exceptionDescription").toString());
            String exceptionStackMsg = jsonObject.get("exceptionStackMsg") == null ? null : jsonObject.get("exceptionStackMsg").toString();
            if (exceptionStackMsg != null && !"".equals(exceptionStackMsg) && exceptionStackMsg.length() > 4000) {
                exceptionStackMsg = exceptionStackMsg.substring(0, 3999);
                sysWebLog.setExceptionStackMsg(exceptionStackMsg);
            }
        }
        List<com.netflix.util.Pair<String, String>> listPair = context.getZuulResponseHeaders();
        for (com.netflix.util.Pair<String, String> info : listPair) {
            if (info.first().equals("X-saas-server-alert")) {
                String message = URLDecoder.decode(info.second(), "UTF-8");
                sysWebLog.setMessages(message);
                break;
            }
        }
        return sysWebLog;
    }

    /**
     * @return
     * @Description 判断请求来源客户端类型
     * @Date
     **/
    private String getRequestSource(HttpServletRequest request) {
        String userAgent = request.getHeader("user-agent").toLowerCase();
        ;
        if (userAgent.indexOf("micromessenger") != -1) {
            //微信
            return "3";
        } else if (userAgent.indexOf("android") != -1) {
            //安卓
            return "1";
        } else if (userAgent.indexOf("iphone") != -1 || userAgent.indexOf("ipad") != -1 || userAgent.indexOf("ipod") != -1) {
            //苹果
            return "2";
        } else {
            //电脑
            return "0";
        }
    }
}
