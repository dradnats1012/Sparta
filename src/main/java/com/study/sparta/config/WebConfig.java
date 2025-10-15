package com.study.sparta.config;

import org.springframework.boot.web.servlet.FilterRegistrationBean;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.CorsRegistry;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

import lombok.RequiredArgsConstructor;

@Configuration
public class WebConfig implements WebMvcConfigurer {

    @Bean
    public FilterRegistrationBean<ServerTimingFilter> serverTimingFilterRegistration(ServerTimingFilter filter) {
        FilterRegistrationBean<ServerTimingFilter> reg = new FilterRegistrationBean<>(filter);
        reg.setOrder(Integer.MAX_VALUE);
        reg.addUrlPatterns("/*");
        return reg;
    }

    @Override
    public void addCorsMappings(CorsRegistry registry) {
        registry.addMapping("/**")
            .allowedOrigins("*")
            .allowedMethods("GET","POST","PUT","DELETE","PATCH","OPTIONS")
            .allowedHeaders("*")
            .exposedHeaders("Server-Timing","Timing-Allow-Origin")
            .maxAge(3600);
    }
}
