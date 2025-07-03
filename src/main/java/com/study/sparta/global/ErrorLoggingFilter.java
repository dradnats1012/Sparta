package com.study.sparta.global;

import java.io.IOException;

import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;

import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@Component
public class ErrorLoggingFilter extends OncePerRequestFilter {

    @Override
    protected void doFilterInternal(HttpServletRequest request,
        HttpServletResponse response,
        FilterChain filterChain)
        throws ServletException, IOException {

        try {
            filterChain.doFilter(request, response);
        } catch (Throwable ex) {
            log.error("""
                    요청 처리 중 오류 발생
                    URI     : {}
                    Method  : {}
                    Query   : {}
                    Message : {}
                    """,
                request.getRequestURI(),
                request.getMethod(),
                request.getQueryString(),
                ex.getMessage(),
                ex
            );
            throw ex;
        }
    }
}