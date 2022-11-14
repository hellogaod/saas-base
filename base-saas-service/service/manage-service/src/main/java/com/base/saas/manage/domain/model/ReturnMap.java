package com.base.saas.manage.domain.model;

import java.util.Map;

/**
 * @className: ReturnMap
 * @author: 佛学徒
 * @date: 2022/10/20
 * @Description：
 **/
public class ReturnMap<T> {
    private int code;
    private String msg;
    private T t;
    private Map map;

    public ReturnMap() {
        this(0, null, null);
    }

    public ReturnMap(int code) {
        this(code, null, null);
    }

    public ReturnMap(int code, String msg) {
        this(code, msg, null);
    }

    public ReturnMap(int code, String msg, T t) {
        this(code, msg, t, null);
    }

    public ReturnMap(int code, String msg, T t, Map map) {
        this.code = code;
        this.msg = msg;
        this.t = t;
        this.map = map;
    }


    public void setCode(int code) {
        this.code = code;
    }

    public void setMsg(String msg) {
        this.msg = msg;
    }

    public void setT(T t) {
        this.t = t;
    }

    public int getCode() {
        return code;
    }

    public String getMsg() {
        return msg;
    }

    public T getT() {
        return t;
    }

    public Map getMap() {
        return map;
    }

    public void setMap(Map map) {
        this.map = map;
    }
}
