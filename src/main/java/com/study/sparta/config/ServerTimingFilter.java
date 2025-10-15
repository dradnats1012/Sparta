package com.study.sparta.config;

import java.io.IOException;

import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;

import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

@Component
public class ServerTimingFilter extends OncePerRequestFilter {
    @Override
    protected void doFilterInternal(HttpServletRequest req, HttpServletResponse res, FilterChain chain)
        throws ServletException, IOException {
        long start = System.nanoTime();
        try {
            chain.doFilter(req, res);
        } finally {
            long durMs = Math.round((System.nanoTime() - start) / 1_000_000.0);
            res.addHeader("Server-Timing", String.format("app;dur=%d;desc=\"Application Processing\"", durMs));
            res.addHeader("Timing-Allow-Origin", "*"); // 필요시 특정 오리진
        }
    }

    @Override
    protected boolean shouldNotFilter(HttpServletRequest req) {
        String uri = req.getRequestURI();
        return uri.startsWith("/actuator") || uri.startsWith("/static") || uri.equals("/favicon.ico");
    }
}