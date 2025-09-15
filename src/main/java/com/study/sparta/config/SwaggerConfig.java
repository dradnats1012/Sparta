package com.study.sparta.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import io.swagger.v3.oas.annotations.OpenAPIDefinition;
import io.swagger.v3.oas.annotations.servers.Server;
import io.swagger.v3.oas.models.Components;
import io.swagger.v3.oas.models.OpenAPI;
import io.swagger.v3.oas.models.info.Info;

@OpenAPIDefinition(
    servers = {
        @Server(url = "https://sparta-api.herjebi.com", description = "spparta https"),
        @Server(url = "http://sparta-api.herjebi.com", description = "sparta http"),
        @Server(url = "http://localhost:8081", description = "sparta local")
    }
)
@Configuration
public class SwaggerConfig {
    @Bean
    public OpenAPI customOpenAPI() {
        return new OpenAPI()
            .components(new Components())
            .info(info());
    }

    private Info info() {
        return new Info()
            .title("Sparta API")
            .description("대량 데이터 프로젝트")
            .version("1.0");
    }
}
