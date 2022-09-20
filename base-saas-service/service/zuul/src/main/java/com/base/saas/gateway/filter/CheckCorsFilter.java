package com.base.saas.gateway.filter;

import org.apache.commons.lang3.StringUtils;
import org.springframework.http.HttpHeaders;
import org.springframework.web.cors.CorsConfigurationSource;
import org.springframework.web.filter.CorsFilter;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * @Author:peishouwen
 * @Desc:
 * @Date:Create in 9:31 2018/3/8
 */
public class CheckCorsFilter extends CorsFilter {
    public CheckCorsFilter(CorsConfigurationSource configSource) {
        super(configSource);
    }

    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain) throws ServletException, IOException {
        if(StringUtils.isBlank(response.getHeader(HttpHeaders.ORIGIN))){
            super.doFilterInternal(request, response, filterChain);
//            super.doFilterInternal(new XssRequestWrapper((HttpServletRequest) request), response, filterChain);
        }

    }


}
