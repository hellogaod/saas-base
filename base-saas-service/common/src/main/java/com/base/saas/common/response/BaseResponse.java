package com.base.saas.common.response;


import com.base.saas.common.constant.AppConstant;
import org.springframework.http.*;
import org.springframework.util.Assert;
import org.springframework.util.MultiValueMap;
import org.springframework.util.ObjectUtils;

import java.net.URI;
import java.util.Arrays;
import java.util.LinkedHashSet;

public class BaseResponse<T> extends HttpEntity<T> {

    private final Object statusCode;


    public BaseResponse(T body, HttpStatus status) {
        this(body, (MultiValueMap)null, (HttpStatus)status);
    }

    public BaseResponse(MultiValueMap<String, String> headers, HttpStatus status) {
        this((T) null, headers, (HttpStatus)status);
    }

    public BaseResponse(T body, MultiValueMap<String, String> headers, HttpStatus status) {
        super(body, headers);
        Assert.notNull(status, "HttpStatus must not be null");
        this.statusCode = status;
    }

    private BaseResponse(T body, MultiValueMap<String, String> headers, Object statusCode) {
        super(body, headers);
        this.statusCode = statusCode;
    }

    public HttpStatus getStatusCode() {
        return this.statusCode instanceof HttpStatus ? (HttpStatus)this.statusCode : HttpStatus.valueOf((Integer)this.statusCode);
    }

    public int getStatusCodeValue() {
        return this.statusCode instanceof HttpStatus ? ((HttpStatus)this.statusCode).value() : (Integer)this.statusCode;
    }

    public boolean equals(Object other) {
        if (this == other) {
            return true;
        } else if (!super.equals(other)) {
            return false;
        } else {
            BaseResponse<?> otherEntity = (BaseResponse)other;
            return ObjectUtils.nullSafeEquals(this.statusCode, otherEntity.statusCode);
        }
    }

    public int hashCode() {
        return super.hashCode() * 29 + ObjectUtils.nullSafeHashCode(this.statusCode);
    }

    public String toString() {
        StringBuilder builder = new StringBuilder("<");
        builder.append(this.statusCode.toString());
        if (this.statusCode instanceof HttpStatus) {
            builder.append(' ');
            builder.append(((HttpStatus)this.statusCode).getReasonPhrase());
        }

        builder.append(',');
        T body = this.getBody();
        HttpHeaders headers = this.getHeaders();
        if (body != null) {
            builder.append(body);
            if (headers != null) {
                builder.append(',');
            }
        }

        if (headers != null) {
            builder.append(headers);
        }

        builder.append('>');
        return builder.toString();
    }

    public static BaseResponse.BodyBuilder status(HttpStatus status) {
        Assert.notNull(status, "HttpStatus must not be null");
        return new BaseResponse.DefaultBuilder(status);
    }

    public static BaseResponse.BodyBuilder status(int status) {
        return new BaseResponse.DefaultBuilder(status);
    }

    public static BaseResponse.BodyBuilder ok() {
        return status(HttpStatus.OK);
    }

    public static <T> BaseResponse<T> ok(T body) {
        BaseResponse.BodyBuilder builder = ok();
        return builder.body(body);
    }

    public static BaseResponse.BodyBuilder created(URI location) {
        BaseResponse.BodyBuilder builder = status(HttpStatus.CREATED);
        return (BaseResponse.BodyBuilder)builder.location(location);
    }

    public static BaseResponse.BodyBuilder accepted() {
        return status(HttpStatus.ACCEPTED);
    }

    public static BaseResponse.HeadersBuilder<?> noContent() {
        return status(HttpStatus.NO_CONTENT);
    }

    public static BaseResponse.BodyBuilder badRequest() {
        return status(HttpStatus.BAD_REQUEST);
    }

    public static BaseResponse.BodyBuilder badRequestForTempPage() {
        return status(AppConstant.TEMP_PAGE_CODE);
    }

    public static BaseResponse.HeadersBuilder<?> notFound() {
        return status(HttpStatus.NOT_FOUND);
    }

    public static BaseResponse.BodyBuilder unprocessableEntity() {
        return status(HttpStatus.UNPROCESSABLE_ENTITY);
    }

    private static class DefaultBuilder implements BaseResponse.BodyBuilder {
        private final Object statusCode;
        private final HttpHeaders headers = new HttpHeaders();

        public DefaultBuilder(Object statusCode) {
            this.statusCode = statusCode;
        }

        public BaseResponse.BodyBuilder header(String headerName, String... headerValues) {
            String[] var3 = headerValues;
            int var4 = headerValues.length;

            for(int var5 = 0; var5 < var4; ++var5) {
                String headerValue = var3[var5];
                this.headers.add(headerName, headerValue);
            }

            return this;
        }

        public BaseResponse.BodyBuilder headers(HttpHeaders headers) {
            if (headers != null) {
                this.headers.putAll(headers);
            }

            return this;
        }

        public BaseResponse.BodyBuilder allow(HttpMethod... allowedMethods) {
            this.headers.setAllow(new LinkedHashSet(Arrays.asList(allowedMethods)));
            return this;
        }

        public BaseResponse.BodyBuilder contentLength(long contentLength) {
            this.headers.setContentLength(contentLength);
            return this;
        }

        public BaseResponse.BodyBuilder contentType(MediaType contentType) {
            this.headers.setContentType(contentType);
            return this;
        }

        public BaseResponse.BodyBuilder eTag(String eTag) {
            if (eTag != null) {
                if (!eTag.startsWith("\"") && !eTag.startsWith("W/\"")) {
                    eTag = "\"" + eTag;
                }

                if (!eTag.endsWith("\"")) {
                    eTag = eTag + "\"";
                }
            }

            this.headers.setETag(eTag);
            return this;
        }

        public BaseResponse.BodyBuilder lastModified(long date) {
            this.headers.setLastModified(date);
            return this;
        }

        public BaseResponse.BodyBuilder location(URI location) {
            this.headers.setLocation(location);
            return this;
        }

        public BaseResponse.BodyBuilder cacheControl(CacheControl cacheControl) {
            String ccValue = cacheControl.getHeaderValue();
            if (ccValue != null) {
                this.headers.setCacheControl(cacheControl.getHeaderValue());
            }

            return this;
        }

        public BaseResponse.BodyBuilder varyBy(String... requestHeaders) {
            this.headers.setVary(Arrays.asList(requestHeaders));
            return this;
        }

        public <T> BaseResponse<T> build() {
            return (BaseResponse<T>) this.body((Object)null);
        }

        public <T> BaseResponse<T> body(T body) {
            return new BaseResponse(body, this.headers, this.statusCode);
        }
    }

    public interface BodyBuilder extends BaseResponse.HeadersBuilder<BaseResponse.BodyBuilder> {
        BaseResponse.BodyBuilder contentLength(long var1);

        BaseResponse.BodyBuilder contentType(MediaType var1);

        <T> BaseResponse<T> body(T var1);
    }

    public interface HeadersBuilder<B extends BaseResponse.HeadersBuilder<B>> {
        B header(String var1, String... var2);

        B headers(HttpHeaders var1);

        B allow(HttpMethod... var1);

        B eTag(String var1);

        B lastModified(long var1);

        B location(URI var1);

        B cacheControl(CacheControl var1);

        B varyBy(String... var1);

        <T> BaseResponse<T> build();
    }
    /**
     * 返回状态
     */
    private Boolean success;

    /**
     * 错误码
     */
    private String errCode;

    /**
     * 错误信息
     */
    private String errorMsg;

    /**
     * 返回数据
     */
    private String result;

    public Boolean getSuccess() {
        return success;
    }

    public void setSuccess(Boolean success) {
        this.success = success;
    }

    public String getErrCode() {
        return errCode;
    }

    public void setErrCode(String errCode) {
        this.errCode = errCode;
    }

    public String getErrorMsg() {
        return errorMsg;
    }

    public void setErrorMsg(String errorMsg) {
        this.errorMsg = errorMsg;
    }

    public String getResult() {
        return result;
    }

    public void setResult(String result) {
        this.result = result;
    }
}
