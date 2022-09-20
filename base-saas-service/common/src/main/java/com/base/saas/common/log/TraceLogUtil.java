package com.base.saas.common.log;

import org.apache.commons.lang3.StringUtils;
import org.slf4j.MDC;

import java.util.UUID;

/**
 * 日志工具类
 * Created by work on 2016/3/2.
 */
public class TraceLogUtil {

    /**
     * 设置日志中的traceLogId字段的值
     * @param traceLogId
     */
    public static void setTraceLogId(String traceLogId) {
        if (StringUtils.isEmpty(traceLogId)) {
            MDC.put(BestpayMarker.TRACE_LOG_ID, createTraceLogId());
        } else {
            MDC.put(BestpayMarker.TRACE_LOG_ID, traceLogId);
        }
    }


    public static String createTraceLogId() {
        return UUID.randomUUID().toString();
    }
    /**
     * 获取日志中的traceLogId字段的值
     */
    public static String getTraceLogId() {
        return MDC.get(BestpayMarker.TRACE_LOG_ID);
    }

    private TraceLogUtil() {
    }

    public static void main(String[] args) {
        TraceLogUtil.setTraceLogId(null);
        System.out.println(TraceLogUtil.getTraceLogId());
    }
}
