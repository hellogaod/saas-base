package com.base.saas.common.userinfo;

import com.base.saas.common.AppConstant;
import com.base.saas.util.StringUtil;
import com.base.saas.util.redis.RedisUtil;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.util.HashMap;
import java.util.Map;

public abstract class UserContextUtil {


    private static ThreadLocal<HashMap<String, Object>> threadLocal = new ThreadLocal<HashMap<String, Object>>() {
        @Override
        protected HashMap<String, Object> initialValue() {
            return new HashMap();
        }
    };

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

    public static UserInfo getUserInfo() {
        return RedisUtil.get(AppConstant.APP_USER_INFO + getUserTokenId());
    }

    public static UserInfo getUserInfo(String tokenId) {
        return RedisUtil.get(AppConstant.APP_USER_INFO + tokenId);
    }

    public static UserInfo getUserInfoByTokenId(String tokenId) {
        return RedisUtil.get(AppConstant.APP_USER_INFO + tokenId);
    }

    public static void setUserInfo(UserInfo userInfo) {
        RedisUtil.set(AppConstant.APP_USER_INFO + getUserTokenId(), userInfo);
    }

    public static void setUserInfo(String tokenId, UserInfo userInfo) {
        RedisUtil.set(AppConstant.APP_USER_INFO + tokenId, userInfo);
    }


    public static String getUserTokenId() {
        String accessToken = getHttpServletRequest().getHeader("X-UserToken");
        if (accessToken != null && StringUtil.isNotEmpty(accessToken)) {
            return accessToken;
        } else {
            return getSession().getId();
        }
    }

    public static <T> T getAttribute(String key) {
        return (T) getSession().getAttribute(key);
    }

    public static <T> void setAttribute(String key, T value) {
        getSession().setAttribute(key, value);
    }

    /**
     * 当前请求功能所拥有的数据查看权限范围
     */
    public static String getDataPermissions() {
        Map<String, String> map = getUserInfo().getDataPermission().get(getHttpServletRequest().getRequestURI());
        if (map != null) {
            return map.get("org_path");
        } else {
            return "";
        }
    }

    /**
     * @return
     * @Description 获取system-web用户数据权限使用
     * @Param
     * @Author coder_bao
     * @Date
     **/
    public static String getDataPermissions(String serverName) {
        String uri = getHttpServletRequest().getRequestURI();
        if (uri != null && !"".equals(uri) && uri.contains("api/")) {
            uri = uri.replace("api/", "");
        }
        Map<String, String> map = getUserInfo().getDataPermission().get("/" + serverName + "" + uri);
        if (map != null) {
            return map.get("org_path");
        } else {
            return "";
        }
    }

    /**
     * 当前请求的功能是否需要进行脱敏处理
     *
     * @return true：需要；false：不需要
     */
    public static boolean isDesensitization() {
        Map<String, String> map = getUserInfo().getDataPermission().get(getHttpServletRequest().getRequestURI());
        if (map != null) {
            String string = map.get("is_desensite");
            if ("0".equals(string)) {
                return false;
            } else {
                return true;
            }
        } else {
            return true;
        }

    }

    public static void localClear() {
        getThreadLocal().get().clear();
        getThreadLocal().remove();
    }
}
