package com.base.saas.userinfo;

import com.base.saas.AppConstant;
import com.base.saas.util.redis.RedisUtil;

import javax.servlet.http.HttpServletRequest;
import java.util.HashMap;

/**
 * 用户信息存取:通过key:sessionId，value：userInfo存储在redis中
 * 1. 如果是登录，将登录request中的session.getId()作为sessionId；
 * 2.将sessionId作为request头部header的x-userToken参数，之后的每次获取都是通过该参数获取；
 */
public abstract class UserContextUtil {

    private static ThreadLocal<HashMap<String, Object>> threadLocal = new ThreadLocal<HashMap<String, Object>>() {
        @Override
        protected HashMap<String, Object> initialValue() {
            return new HashMap();
        }
    };

    //传递当前Request对象
    public static void setHttpServletRequest(HttpServletRequest httpServletRequest) {
        threadLocal.get().put("httpServletRequest", httpServletRequest);
    }

    private static ThreadLocal<HashMap<String, Object>> getThreadLocal() {
        return threadLocal;
    }

    public static HttpServletRequest getHttpServletRequest() {
        return (HttpServletRequest) threadLocal.get().get("httpServletRequest");
    }

    //通过header获取sessionId
    public static String getUserTokenId() {
        if (getHttpServletRequest() != null)
            return getHttpServletRequest().getHeader("X-UserToken");
        return null;
    }

    //通过header获取sessionId
    public static String getUserTokenId(HttpServletRequest httpServletRequest) {
        String token = httpServletRequest.getHeader("X-UserToken");
        return token;
    }

    //通过request获取sessionid作为key，再在redis中获取userInfo
    public static UserInfo getUserInfo() {
        if (getHttpServletRequest() == null)
            return null;
        return RedisUtil.get(AppConstant.APP_USER_INFO + getUserTokenId());
    }

    //如果是登录情况下，将登录request中国的sessionId作为tokenId
    public static UserInfo getUserInfo(String tokenId) {
        if (tokenId == null) {
            return null;
        }
        return RedisUtil.get(AppConstant.APP_USER_INFO + tokenId);
    }

    public static void setUserInfo(UserInfo userInfo) {
        RedisUtil.set(AppConstant.APP_USER_INFO + getUserTokenId(), userInfo);
    }

    public static void setUserInfo(String tokenId, UserInfo userInfo) {
        RedisUtil.set(AppConstant.APP_USER_INFO + tokenId, userInfo);
    }

}
