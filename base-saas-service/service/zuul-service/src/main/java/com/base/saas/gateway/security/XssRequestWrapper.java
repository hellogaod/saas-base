package com.base.saas.gateway.security;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.servlet.ReadListener;
import javax.servlet.ServletInputStream;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletRequestWrapper;
import java.io.BufferedReader;
import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.nio.charset.Charset;
import java.util.HashMap;
import java.util.Map;

/**
 * @ClassName XssRequestWrapper
 * @Description
 * @Author coder_bao
 * @Date 2018/8/29 16:01
 */
public class XssRequestWrapper extends HttpServletRequestWrapper {

    private static final Logger logger = LoggerFactory.getLogger(XssRequestWrapper.class);

    /**
     * 判断是否是上传 上传忽略
     */
    boolean isUpData = false;

    public XssRequestWrapper(HttpServletRequest request) {
        super(request);
        String contentType = request.getContentType();
        if (null != contentType) {
            isUpData = contentType.startsWith("multipart");
        }
    }

    @SuppressWarnings("unchecked")
    @Override
    public Map<String, String[]> getParameterMap() {
        Map<String, String[]> parameterMap = super.getParameterMap();
        Map<String, String[]> objectMap = new HashMap(parameterMap.size());
        for (String key : parameterMap.keySet()) {
            String[] objectArray = parameterMap.get(key);
            int size = objectArray.length;
            for (int i = 0; i < size; i++) {
                objectArray[i] = XssClean.xssClean(objectArray[i]);
            }
            objectMap.put(key, objectArray);
        }
        return objectMap;
    }

    @Override
    public String getQueryString() {
        String value = super.getQueryString();
        return XssClean.xssClean(value);
    }


    @Override
    public String getParameter(String name) {
        String value = super.getParameter(name);
        return XssClean.xssClean(value);
    }

    @Override
    public String[] getParameterValues(String name) {
        String[] parameterValues = super.getParameterValues(name);
        if (null != parameterValues) {
            int pLength = parameterValues.length;
            String[] strs = new String[pLength];
            for (int i = 0; i < pLength; i++) {
                String value = parameterValues[i];
                strs[i] = XssClean.xssClean(value);
            }
            return strs;
        }
        return parameterValues;
    }

    @Override
    public ServletInputStream getInputStream() throws IOException {
        if (isUpData) {
            return super.getInputStream();
        } else {
            return new CustomServletInputStream(inputHandlers(super.getInputStream()));
        }
    }

    private String inputHandlers(ServletInputStream servletInputStream) {
        StringBuilder sb = new StringBuilder();
        BufferedReader reader = null;
        try {
            reader = new BufferedReader(new InputStreamReader(servletInputStream, Charset.forName("UTF-8")));
            String line;
            while ((line = reader.readLine()) != null) {
                sb.append(line);
            }
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            if (servletInputStream != null) {
                try {
                    servletInputStream.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
            if (reader != null) {
                try {
                    reader.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }
        String value = sb.toString();
        return XssClean.xssClean(value);
    }

    private class CustomServletInputStream extends ServletInputStream {
        private ByteArrayInputStream buffer;

        public CustomServletInputStream(String body) {
            body = body == null ? "" : body;
            this.buffer = new ByteArrayInputStream(body.getBytes());
        }

        @Override
        public int read() {
            return buffer.read();
        }

        @Override
        public boolean isFinished() {
            return buffer.available() == 0;
        }

        @Override
        public boolean isReady() {
            return true;
        }

        @Override
        public void setReadListener(ReadListener listener) {
        }
    }
}
