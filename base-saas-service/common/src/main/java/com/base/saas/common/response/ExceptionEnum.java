package com.base.saas.common.response;
/**
*@Description 自定义异常
*@Param 
*@return 
*@Author coder_bao
*@Date  
**/
public enum ExceptionEnum {
    EXCEPTION_CODE(100,"system.server.exception"),
    TIME_OUT_EXCEPTION_CODE(408,"server.connection.timeout");




    private final int code;
    private final String description;

    ExceptionEnum(int code, String description) {
        this.code = code;
        this.description = description;
    }

    public int code() {
        return this.code;
    }

    public String description() {
        return this.description;
    }
}
