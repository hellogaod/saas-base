package com.base.saas.util;

import java.io.PrintWriter;
import java.io.StringWriter;

/**
 * @ClassName ExceptionStackUtils
 * @Description 收集异常堆栈信息
 * @Date 2018/9/3 13:44
 */
public class ExceptionStackUtils {
    public static String collectExceptionStackMsg(Exception e){
        StringWriter sw = new StringWriter();
        e.printStackTrace(new PrintWriter(sw, true));
        String strs = sw.toString();
        return strs;
    }
}
