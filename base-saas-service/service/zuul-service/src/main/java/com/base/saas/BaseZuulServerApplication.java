package com.base.saas;

import com.base.saas.gateway.filter.AccessFilter;
import com.base.saas.gateway.filter.PostFilter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.condition.ConditionalOnMissingBean;
import org.springframework.boot.autoconfigure.jdbc.DataSourceAutoConfiguration;
import org.springframework.boot.autoconfigure.orm.jpa.HibernateJpaAutoConfiguration;
import org.springframework.boot.autoconfigure.web.MultipartProperties;
import org.springframework.boot.builder.SpringApplicationBuilder;
import org.springframework.cloud.client.circuitbreaker.EnableCircuitBreaker;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.cloud.client.loadbalancer.LoadBalanced;
import org.springframework.cloud.netflix.zuul.EnableZuulProxy;
import org.springframework.context.annotation.Bean;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.cors.CorsConfiguration;
import org.springframework.web.cors.UrlBasedCorsConfigurationSource;
import org.springframework.web.filter.CorsFilter;

import javax.servlet.MultipartConfigElement;

//普通Zuul Server+服务发现与熔断等功能的增强版,具有反向代理功能
@EnableZuulProxy
//exclude属性值表示可无数据库运行
@SpringBootApplication(exclude = {DataSourceAutoConfiguration.class, HibernateJpaAutoConfiguration.class})
//将微服务注册到服务发现组件
@EnableDiscoveryClient
//在SpringCloud中使用断路器
@EnableCircuitBreaker
public class BaseZuulServerApplication {

    @Autowired
    private MultipartProperties multipartProperties;

    public static void main(String[] args) {
        new SpringApplicationBuilder(BaseZuulServerApplication.class).web(true).run(args);
    }

    @Bean
    @LoadBalanced
    RestTemplate restTemplate() {
        return new RestTemplate();
    }

    //springboot跨域请求
    @Bean
    public CorsFilter corsFilter() {
        final UrlBasedCorsConfigurationSource urlBasedCorsConfigurationSource = new UrlBasedCorsConfigurationSource();
        final CorsConfiguration corsConfig = new CorsConfiguration();
        corsConfig.setAllowCredentials(true);
        // * 表示对所有的地址都可以访问
        corsConfig.addAllowedOrigin("*");

        //跨域的请求头:表示允许自定义header输出
        corsConfig.addAllowedHeader("*");
        corsConfig.addExposedHeader("saas-error-message");
        corsConfig.addExposedHeader("saas-token");

        //跨域的请求方法
        corsConfig.addAllowedMethod("OPTIONS");
        corsConfig.addAllowedMethod("HEAD");
        corsConfig.addAllowedMethod("GET");
        corsConfig.addAllowedMethod("PUT");
        corsConfig.addAllowedMethod("POST");
        corsConfig.addAllowedMethod("DELETE");
        corsConfig.addAllowedMethod("PATCH");
        urlBasedCorsConfigurationSource.registerCorsConfiguration("/**", corsConfig);
        return new CorsFilter(urlBasedCorsConfigurationSource);
    }

    //Spring提供了内置的multipart支持来处理Web应用程序文件上传
    @Bean
    @ConditionalOnMissingBean
    public MultipartConfigElement multipartConfigElement() {
        this.multipartProperties.setMaxFileSize("1024MB");
        this.multipartProperties.setMaxRequestSize("1024MB");
        return this.multipartProperties.createMultipartConfig();
    }

    @Bean
    public AccessFilter accessFilter() {
        return new AccessFilter();
    }

    @Bean
    public PostFilter postFilter() {
        return new PostFilter();
    }
}
