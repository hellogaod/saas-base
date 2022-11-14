package com.base.saas.util;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpHeaders;

import java.io.UnsupportedEncodingException;
import java.net.URLEncoder;

/**
 * Utility class for HTTP headers creation.
 */
public final class HeaderUtil {

    private static final Logger log = LoggerFactory.getLogger(HeaderUtil.class);

    private HeaderUtil() {
    }

    public static HttpHeaders createErrorMsg(String message) {
        HttpHeaders headers = new HttpHeaders();
        try {
            headers.add("saas-error-message", URLEncoder.encode(message, "UTF-8"));
        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
        }
        return headers;
    }

    public static HttpHeaders createToken(String sessionId) {
        HttpHeaders headers = new HttpHeaders();
        try {
            headers.add("saas-token", URLEncoder.encode(sessionId, "UTF-8"));
        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
        }
        return headers;
    }
}
