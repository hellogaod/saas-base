package com.base.saas.common.exception;

/**
 * @author HLH
 * @description: 自定义异常
 * @email 17703595860@163.com
 * @date : Created in 2022/2/4 11:09
 */
public class CustomizeException extends Exception {

    public CustomizeException(String message, Throwable cause) {
        super(message, cause);
    }

    public CustomizeException(String message) {
        super(message);
    }

}
