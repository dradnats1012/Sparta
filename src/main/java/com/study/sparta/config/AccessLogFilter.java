package com.study.sparta.config;

import jakarta.servlet.*;
import jakarta.servlet.http.HttpServletRequest;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.core.annotation.Order;
import org.springframework.stereotype.Component;
import java.io.IOException;

@Component
@Order(1)
public class AccessLogFilter implements Filter {

    private static final Logger logger = LoggerFactory.getLogger("ACCESS_LOGGER");

    @Override
    public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain)
        throws IOException, ServletException {
        HttpServletRequest req = (HttpServletRequest) request;
        String requestURI = req.getRequestURI();

        if ("/sparta/prometheus".equals(requestURI) || "/sparta/health".equals(requestURI)) {
            chain.doFilter(request, response);
            return;
        }

        String remoteAddr = req.getHeader("X-Forwarded-For");
        if (remoteAddr == null || remoteAddr.isEmpty() || "unknown".equalsIgnoreCase(remoteAddr)) {
            remoteAddr = req.getRemoteAddr();
        }

        String method = req.getMethod();

        logger.info("{} {} {}", remoteAddr, method, requestURI);

        chain.doFilter(request, response);
    }
}
