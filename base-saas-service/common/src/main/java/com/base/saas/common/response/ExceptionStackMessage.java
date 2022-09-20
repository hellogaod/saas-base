package com.base.saas.common.response;

import java.io.PrintWriter;
import java.io.StringWriter;

/**
 * @ClassName ExceptionStackMessage
 * @Description 收集异常堆栈信息
 * @Author coder_bao
 * @Date 2018/9/3 13:44
 */
public class ExceptionStackMessage {
    public static String collectExceptionStackMsg(Exception e){
        StringWriter sw = new StringWriter();
        e.printStackTrace(new PrintWriter(sw, true));
        String strs = sw.toString();
        return strs;
//        return e.toString();
    }
}
