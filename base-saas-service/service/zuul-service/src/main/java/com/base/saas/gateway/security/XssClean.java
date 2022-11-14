package com.base.saas.gateway.security;

import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.HashMap;
import java.util.Map;

public class XssClean {
    private static final Logger logger = LoggerFactory.getLogger(XssClean.class);

    private static final Map<String, String> KEYS_MAP = new HashMap();

    static {
        KEYS_MAP.put("(?i)&quot;", "\"");
        KEYS_MAP.put("(?i)select ", "#select #");
        KEYS_MAP.put("(?i)update ", "#update #");
        KEYS_MAP.put("(?i)delete ", "#delete #");
        KEYS_MAP.put("(?i)insert ", "#insert #");
        KEYS_MAP.put("(?i)<script>", "#<script>#");
        KEYS_MAP.put("(?i)system ", "#system #");
        KEYS_MAP.put("(?i)databases", "#databases#");
        KEYS_MAP.put("(?i)show ", "#show #");
        KEYS_MAP.put("(?i) and ", "# and #");
        KEYS_MAP.put("(?i)substr", "#substr #");
        KEYS_MAP.put("(?i)drop ", "#drop #");
        KEYS_MAP.put("(?i)sleep", "#sleep #");
    }

    public static String xssClean(String value) {
        if (StringUtils.isNotEmpty(value)) {
            try {
                for (String key : KEYS_MAP.keySet()) {
                    value = value.replaceAll(key, KEYS_MAP.get(key));
                }
            } catch (Exception e) {
                logger.error("过滤XSS异常", e);
            }
        }
        return value;
    }
}
