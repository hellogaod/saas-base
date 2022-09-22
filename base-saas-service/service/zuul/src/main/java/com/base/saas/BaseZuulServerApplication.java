package com.base.saas;


import com.base.saas.gateway.filter.AccessFilter;
import com.base.saas.gateway.filter.CheckCorsFilter;
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

@EnableZuulProxy
@SpringBootApplication(exclude={DataSourceAutoConfiguration.class,HibernateJpaAutoConfiguration.class})
@EnableDiscoveryClient
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

    @Bean
    public CorsFilter corsFilter() {
        final UrlBasedCorsConfigurationSource urlBasedCorsConfigurationSource = new UrlBasedCorsConfigurationSource();
        final CorsConfiguration corsConfig = new CorsConfiguration();
        corsConfig.setAllowCredentials(true);
        corsConfig.addAllowedOrigin("*");
        corsConfig.addAllowedHeader("*");
        corsConfig.addExposedHeader("X-saas-server-error");
        corsConfig.addExposedHeader("X-saas-server-alert");
        corsConfig.addExposedHeader("X-saas-server-params");
        corsConfig.addAllowedMethod("OPTIONS");
        corsConfig.addAllowedMethod("HEAD");
        corsConfig.addAllowedMethod("GET");
        corsConfig.addAllowedMethod("PUT");
        corsConfig.addAllowedMethod("POST");
        corsConfig.addAllowedMethod("DELETE");
        corsConfig.addAllowedMethod("PATCH");
        urlBasedCorsConfigurationSource.registerCorsConfiguration("/**", corsConfig);
        return new CheckCorsFilter(urlBasedCorsConfigurationSource);
    }

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
    public PostFilter postFilter(){
        return new PostFilter();
    }
}
