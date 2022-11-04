package com.base.saas.userinfo;

import com.base.saas.AppConstant;
import com.base.saas.util.redis.RedisUtil;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.util.HashMap;

/**
 * 用户信息存取
 */
public abstract class UserContextUtil {

    private static ThreadLocal<HashMap<String, Object>> threadLocal = ThreadLocal.withInitial(() -> new HashMap());

    private static ThreadLocal<HashMap<String, Object>> getThreadLocal() {
        return threadLocal;
    }

    public static void setHttpServletRequest(HttpServletRequest httpServletRequest) {
        threadLocal.get().put("httpServletRequest", httpServletRequest);
    }

    public static void setHttpServletResponse(HttpServletResponse httpServletResponse) {
        threadLocal.get().put("httpServletResponse", httpServletResponse);
    }

    public static HttpSession getSession() {
        HttpServletRequest request = getHttpServletRequest();
        return request.getSession();
    }

    public static HttpServletRequest getHttpServletRequest() {
        return (HttpServletRequest) threadLocal.get().get("httpServletRequest");
    }

    public static HttpServletResponse getHttpServletResponse() {
        return (HttpServletResponse) threadLocal.get().get("httpServletResponse");
    }

    public static String getUserTokenId() {

        return getSession().getId();
    }

    public static UserInfo getUserInfo() {
        return RedisUtil.get(AppConstant.APP_USER_INFO + getUserTokenId());
    }

    public static UserInfo getUserInfo(String tokenId) {
        return RedisUtil.get(AppConstant.APP_USER_INFO + tokenId);
    }

    public static void setUserInfo(UserInfo userInfo) {
        RedisUtil.set(AppConstant.APP_USER_INFO + getUserTokenId(), userInfo);
    }

    public static void setUserInfo(String tokenId, UserInfo userInfo) {
        RedisUtil.set(AppConstant.APP_USER_INFO + tokenId, userInfo);
    }

    public static void localClear() {
        getThreadLocal().get().clear();
        getThreadLocal().remove();
    }
}
