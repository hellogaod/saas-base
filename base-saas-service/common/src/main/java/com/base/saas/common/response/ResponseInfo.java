package com.base.saas.common.response;


import com.base.saas.common.userinfo.UserContextUtil;
import com.base.saas.common.userinfo.UserInfo;

import java.io.Serializable;

/**
 * @ClassName ResponseInfo
 * @Description TODO
 * @Author coder_bao
 * @Date 2018/9/3 8:55
 */
public class ResponseInfo<T> implements Serializable{
    private int statusCode;//api执行状态
//    private String message;//api执行描述
    private String sessionId= UserContextUtil.getUserTokenId();
    private int exceptionCode;//异常code
    private String exceptionDescription;//异常简单描述
    private String exceptionStackMsg;//异常堆栈信息

    private UserInfo userInfo=UserContextUtil.getUserInfo();;//登录时使用
    private T responseBody;


    public void initResponseInfo(int statusCode, int exceptionCode, String exceptionDescription, String exceptionStackMsg) {
        this.statusCode = statusCode;
        this.exceptionCode = exceptionCode;
        this.exceptionDescription = exceptionDescription;
        this.exceptionStackMsg = exceptionStackMsg;
    }

    public UserInfo getUserInfo() {
        return userInfo;
    }

    public void setUserInfo(UserInfo userInfo) {
        this.userInfo = userInfo;
    }

    public String getSessionId() {
        return sessionId;
    }

    public void setSessionId(String sessionId) {
        this.sessionId = sessionId;
    }

    public T getResponseBody() {
        return responseBody;
    }

    public void setResponseBody(T responseBody) {
        this.responseBody = responseBody;
    }

    public int getStatusCode() {
        return statusCode;
    }

    public void setStatusCode(int statusCode) {
        this.statusCode = statusCode;
    }


    public int getExceptionCode() {
        return exceptionCode;
    }

    public void setExceptionCode(int exceptionCode) {
        this.exceptionCode = exceptionCode;
    }

    public String getExceptionDescription() {
        return exceptionDescription;
    }

    public void setExceptionDescription(String exceptionDescription) {
        this.exceptionDescription = exceptionDescription;
    }

    public String getExceptionStackMsg() {
        return exceptionStackMsg;
    }

    public void setExceptionStackMsg(String exceptionStackMsg) {
        this.exceptionStackMsg = exceptionStackMsg;
    }
}
