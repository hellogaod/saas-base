package com.base.saas.gateway.filter;


import com.base.saas.common.constant.AppConstant;
import com.base.saas.common.userinfo.UserContextUtil;
import com.base.saas.common.userinfo.UserInfo;
import com.base.saas.util.redis.RedisKeyConstants;
import com.base.saas.util.redis.RedisUtil;
import com.netflix.zuul.ZuulFilter;
import com.netflix.zuul.context.RequestContext;
import lombok.SneakyThrows;
import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.net.URLEncoder;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;


public class AccessFilter extends ZuulFilter {

    private static Logger log = LoggerFactory.getLogger(AccessFilter.class);
    @Value("${sessionTimeout}")
    private String sessionTimeout;

    @Override
    public String filterType() {
        return "pre";
    }

    @Override
    public int filterOrder() {
        return 0;
    }

    public boolean shouldFilter() {
        return true;
    }
    @SneakyThrows
    public Object run() {
        RequestContext ctx = RequestContext.getCurrentContext();
        HttpServletRequest request = ctx.getRequest();
        HttpServletResponse response = ctx.getResponse();
        log.debug(String.format("%s request to %s", request.getMethod(), request.getRequestURL().toString()));
        String accessToken = request.getHeader("X-UserToken");
        if (StringUtils.contains(request.getRequestURL().toString(), "/api/companyController")
                || StringUtils.containsIgnoreCase(request.getRequestURL().toString(), "getAllCompany")
                || StringUtils.containsIgnoreCase(request.getRequestURL().toString(), "fileUploadController/view")) {
            return null;
        }
        if (StringUtils.contains(request.getRequestURL().toString(), "api/login/doLogin")
                || StringUtils.contains(request.getRequestURL().toString(), "swagger")
                || StringUtils.contains(request.getRequestURL().toString(), "api-docs")
                || StringUtils.containsIgnoreCase(request.getRequestURL().toString(), "HashCode")
                || StringUtils.containsIgnoreCase(request.getRequestURL().toString(), "resourceController")
                || StringUtils.containsIgnoreCase(request.getRequestURL().toString(), "dataDictController")
                || StringUtils.containsIgnoreCase(request.getRequestURL().toString(), "api/syslogin/syslogin")
                || StringUtils.containsIgnoreCase(request.getRequestURL().toString(), "api/validate/code/get")
                || StringUtils.containsIgnoreCase(request.getRequestURL().toString(), "api/validate/code/check")
                || StringUtils.containsIgnoreCase(request.getRequestURL().toString(), "test/feign/testper")
                || StringUtils.containsIgnoreCase(request.getRequestURL().toString(), "api/syslogin/loadindex")
                || StringUtils.containsIgnoreCase(request.getRequestURL().toString(), "/api/registerAndLogin/getProtocol")
                || StringUtils.containsIgnoreCase(request.getRequestURL().toString(), "/api/registerAndLogin/doRegister")
                || StringUtils.containsIgnoreCase(request.getRequestURL().toString(), "/api/updatePassword/checkVerify")
                || StringUtils.containsIgnoreCase(request.getRequestURL().toString(), "/api/updatePassword/forgetPassword")
                || StringUtils.containsIgnoreCase(request.getRequestURL().toString(), "/api/smsCode/doSendSmsCode")
                || StringUtils.containsIgnoreCase(request.getRequestURL().toString(), "/api/registerAndLogin/doLogin")
                || StringUtils.containsIgnoreCase(request.getRequestURL().toString(), "/api/product/getProductInfo")
                || StringUtils.containsIgnoreCase(request.getRequestURL().toString(), "/api/product/getProductCustList")
                || StringUtils.containsIgnoreCase(request.getRequestURL().toString(), "/api/upload")
                || StringUtils.containsIgnoreCase(request.getRequestURL().toString(), "/api/zr")

                || StringUtils.contains(request.getRequestURL().toString(), "/ran-fenqi-app-service")
                //|| StringUtils.containsIgnoreCase(request.getRequestURL().toString(), "/api/third/sms/sendCode")
                //|| StringUtils.containsIgnoreCase(request.getRequestURL().toString(), "/api/third/login/doCodeLogin")
                //|| StringUtils.containsIgnoreCase(request.getRequestURL().toString(), "/api/third/login/hasRegister")
                //|| StringUtils.containsIgnoreCase(request.getRequestURL().toString(), "/api/third/protocol/getProtocolByType")
                //|| StringUtils.containsIgnoreCase(request.getRequestURL().toString(), "/api/third/protocol/getProtocolListByType")
                //|| StringUtils.containsIgnoreCase(request.getRequestURL().toString(), "/api/third/protocol/getProtocolById")
                //|| StringUtils.containsIgnoreCase(request.getRequestURL().toString(), "/api/third/order/hasNotFinishedOrder")
                //|| StringUtils.containsIgnoreCase(request.getRequestURL().toString(), "/api/third/order/hasNotFinishedOrder")
                //|| StringUtils.containsIgnoreCase(request.getRequestURL().toString(), "/api/third/order/isCreditExpire")
                //|| StringUtils.containsIgnoreCase(request.getRequestURL().toString(), "/api/third/order/isCreditEnough")
                //|| StringUtils.containsIgnoreCase(request.getRequestURL().toString(), "/api/third/grant/apply")
                //|| StringUtils.containsIgnoreCase(request.getRequestURL().toString(), "/api/third/grant/query")

                //|| StringUtils.containsIgnoreCase(request.getRequestURL().toString(), "/api/apply/getCustApply")
                //|| StringUtils.containsIgnoreCase(request.getRequestURL().toString(), "/api/loan/goRepaymentInfo")
                //|| StringUtils.containsIgnoreCase(request.getRequestURL().toString(), "/api/ocr/info")
                //|| StringUtils.containsIgnoreCase(request.getRequestURL().toString(), "/api/ocr/getOcrInfo")
                //|| StringUtils.containsIgnoreCase(request.getRequestURL().toString(), "/api/apply/getCustApply")

        ) {
            return null;

        }
        UserContextUtil.setHttpServletRequest(request);
        UserContextUtil.setHttpServletResponse(response);
        UserInfo userInfo = UserContextUtil.getUserInfo();
        String url = request.getRequestURI();
        System.out.println(request.getRequestURI()+": "+UserContextUtil.getUserTokenId());
        if (userInfo != null)
        {
            if (userInfo.getUserType() == 1 && AppConstant.ADMIN_USERNAME.equals(userInfo.getAccount())) {
                //运营系统管理员
                RedisUtil.expire(RedisKeyConstants.LOGIN_PREFIX + userInfo.getAccount(), Integer.parseInt(sessionTimeout));
                return null;
            }else if (userInfo.getUserType() == 2 && !"".equalsIgnoreCase(userInfo.getCompanyCode())){
                //企业用户
                RedisUtil.expire(RedisKeyConstants.LOGIN_PREFIX + userInfo.getCompanyCode() + "-" + userInfo.getAccount(), Integer.parseInt(sessionTimeout));
            }else if (userInfo.getUserType() == 3 && !"".equalsIgnoreCase(userInfo.getCompanyCode())){
                //app用户
                String loginIp =request.getRemoteAddr();
                RedisUtil.set(RedisKeyConstants.LOGIN_PREFIX + userInfo.getCompanyCode() + "-" + userInfo.getUserId(), loginIp, Integer.parseInt(sessionTimeout));
                return null;
            }
            //1，判断当前url是否在数据库中，不在的情况代表是公共url，均可访问
            Set allPermissions = ((Map<String, HashSet<String>>) RedisUtil.get(RedisKeyConstants.MENU_LIST)).get(userInfo.getCompanyCode());
            //处理数据库菜单uri配置和实际不符情况
            if (url!=null&&!"".equals(url)&&url.contains("api")){
                url = url.replace("/api","");
            }
            if (!allPermissions.contains(url)) {
                return null;
            }
            //2, 当前url在数据库中，继续判断当前用户是否对此url有权限访问
            List<String> permission_url_lists = userInfo.getPermissionList();
            if (permission_url_lists!=null&&permission_url_lists.size()>0){
                //判断当前访问url是否在当前用户权限url范围内
                if (permission_url_lists.contains(url)){
                    return null;
                }
            }else {
                log.debug("请联系管理员分配菜单访问权限");
                ctx.setSendZuulResponse(false);
                ctx.setResponseStatusCode(403);
                return null;
            }
        }
        else
        {
            log.debug("Invalid user login information");
            ctx.setSendZuulResponse(false);
            ctx.setResponseStatusCode(401);
            response.addHeader("x-saas-server-error", "error.system.server.exception");
            response.addHeader("x-saas-server-alert", URLEncoder.encode("token失效，请重新登录", "UTF-8"));
            response.addHeader("x-saas-access-token-expired", "accessFilterToken");
            return null;
        }
        return null;
    }
}
