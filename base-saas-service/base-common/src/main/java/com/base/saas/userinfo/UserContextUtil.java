package com.base.saas.userinfo;

import com.base.saas.AppConstant;
import com.base.saas.util.redis.RedisUtil;

import javax.servlet.http.HttpServletRequest;

/**
 * 用户信息存取:通过key:sessionId，value：userInfo存储在redis中
 * 1. 如果是登录，将登录request中的session.getId()作为sessionId；
 * 2.将sessionId作为request头部header的x-userToken参数，之后的每次获取都是通过该参数获取；
 */
public abstract class UserContextUtil {

    private static HttpServletRequest httpServletRequest = null;

    //传递当前Request对象
    public static void setHttpServletRequest(HttpServletRequest request) {
        httpServletRequest = request;
    }


    //通过header获取sessionId
    public static String getUserTokenId() {
        if (httpServletRequest != null)
            return httpServletRequest.getHeader("X-UserToken");
        return null;
    }

    //通过request获取sessionid作为key，再在redis中获取userInfo
    public static UserInfo getUserInfo() {
        if (httpServletRequest == null)
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
