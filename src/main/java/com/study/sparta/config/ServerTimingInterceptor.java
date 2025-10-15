package com.study.sparta.config;

import org.springframework.stereotype.Component;
import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.ModelAndView;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

@Component
public class ServerTimingInterceptor implements HandlerInterceptor {

    private static final String START_TIME = "startTime";

    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object Handler) throws Exception{
        request.setAttribute(START_TIME, System.currentTimeMillis());
        return true;
    }

    @Override
    public void postHandle(HttpServletRequest request, HttpServletResponse response, Object handler, ModelAndView modelAndView) throws Exception{
        Long startTime = (Long) request.getAttribute(START_TIME);
        if(startTime != null){
            long duration = System.currentTimeMillis() - startTime;

            String serverTimingHeader = String.format("app;dur=%d;desc=\"Application Processing\"", duration);

            response.addHeader("Server-Timing", serverTimingHeader);
        }
    }
}
