package com.study.sparta;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.scheduling.annotation.EnableAsync;

@EnableAsync
@SpringBootApplication
public class SpartaApplication {

    public static void main(String[] args) {
        SpringApplication.run(SpartaApplication.class, args);
    }

}
