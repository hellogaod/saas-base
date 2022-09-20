package com.base.saas.common.response;

import com.base.saas.common.userinfo.UserContextUtil;
import org.springframework.http.HttpStatus;

/**
 * @ClassName ResponseLogMessageHandel
 * @Description 日志信息对象工具类
 * @Author coder_bao
 * @Date 2018/9/10 15:59
 */
public class ResponseLogMessageHandel {
    private volatile static ResponseInfo responseInfo ;
    /**
    *@Description 创建单例对象
    *@Param
    *@return
    *@Author coder_bao
    *@Date
    **/
    private static ResponseInfo getInstance() {
        if (responseInfo == null){
            synchronized (ResponseInfo.class) {
                if (responseInfo == null) {
                    responseInfo = new ResponseInfo();
                }
            }
        }
        responseInfo.setSessionId(UserContextUtil.getUserTokenId());
        responseInfo.setUserInfo(UserContextUtil.getUserInfo());
        return responseInfo;
    }
    /**
     *@Description api执行失败但无异常空返回情况初始化返回结果
     *@Param message 成功描述
     *@return
     *@Author coder_bao
     *@Date
     **/
    public static ResponseInfo initExceptionResponseData(){
        return initResponseData(HttpStatus.BAD_REQUEST.value(),0,null,null,null);
    }
    /**
     * @Description 系统异常返回封装方法
     * @Param 异常
     * @return
     * @Author coder_bao
     * @Date 2018/9/28 9:45
     */
    public static ResponseInfo initExceptionDataResponse(Exception e){
        return initResponseData(HttpStatus.BAD_REQUEST.value(),ExceptionEnum.EXCEPTION_CODE.code(),
                ExceptionEnum.EXCEPTION_CODE.description(),ExceptionStackMessage.collectExceptionStackMsg(e),null);
    }
    /**
     * @Description 响应超时异常返回封装方法
     * @Param 异常
     * @return
     * @Author coder_bao
     * @Date 2018/9/28 9:45
     */
    public static ResponseInfo timeOutExceptionDataResponse(Exception e){
        return initResponseData(HttpStatus.BAD_REQUEST.value(),ExceptionEnum.TIME_OUT_EXCEPTION_CODE.code(),
                ExceptionEnum.TIME_OUT_EXCEPTION_CODE.description(),ExceptionStackMessage.collectExceptionStackMsg(e),null);
    }
    /**
    *@Description api执行成功情况初始化返回结果
    *@Param message 成功描述
     *@param object 返回给前端的对象
    *@return
    *@Author coder_bao
    *@Date
    **/
    public static ResponseInfo initSuccessResponseData(Object object){
        return initResponseData(HttpStatus.OK.value(),0,null,null,object);
    }
    /**
    *@Description 初始化请求返回结果
    *@Param
    *@return
    *@Author coder_bao
    *@Date
    **/
    public static ResponseInfo initResponseData(int statusCode,int exceptionCode,String exceptionDescription
                                    ,String exceptionStackMsg,Object responseBody){
        ResponseInfo newResponseInfo = getInstance();
        if (newResponseInfo!=null){
            newResponseInfo.setStatusCode(statusCode);
            newResponseInfo.setExceptionCode(exceptionCode);
            newResponseInfo.setExceptionDescription(exceptionDescription);
            newResponseInfo.setExceptionStackMsg(exceptionStackMsg);
            newResponseInfo.setResponseBody(responseBody);
        }
        return newResponseInfo;
    }
}
