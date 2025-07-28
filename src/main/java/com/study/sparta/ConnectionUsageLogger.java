package com.study.sparta;

import lombok.extern.slf4j.Slf4j;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Aspect;
import org.springframework.stereotype.Component;

import java.lang.reflect.Proxy;
import java.sql.Connection;
import java.util.concurrent.atomic.AtomicInteger;
import java.util.concurrent.atomic.AtomicLong;

@Slf4j
@Aspect
@Component
public class ConnectionUsageLogger {

    private final AtomicLong totalConnectionTime = new AtomicLong(0);
    private final AtomicInteger connectionCount = new AtomicInteger(0);

    @Around("execution(* javax.sql.DataSource.getConnection(..))")
    public Object logConnectionUsage(ProceedingJoinPoint joinPoint) throws Throwable {
        long start = System.currentTimeMillis();
        Connection connection = (Connection) joinPoint.proceed();

        return Proxy.newProxyInstance(
            connection.getClass().getClassLoader(),
            new Class[]{Connection.class},
            (proxy, method, args) -> {
                if (method.getName().equals("close")) {
                    long duration = System.currentTimeMillis() - start;

                    totalConnectionTime.addAndGet(duration);
                    int count = connectionCount.incrementAndGet();

                    if (count % 50 == 0) { // 50개 단위로 평균 출력
                        long avg = totalConnectionTime.get() / count;
                        log.info("💡 현재까지 커넥션 평균 점유 시간: {}ms (총 {}건)", avg, count);
                    }

                    log.debug("커넥션 점유 시간: {}ms", duration);
                }
                return method.invoke(connection, args);
            }
        );
    }
}