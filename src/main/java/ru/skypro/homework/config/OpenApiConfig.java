package ru.skypro.homework.config;


import io.swagger.v3.oas.models.OpenAPI;
import io.swagger.v3.oas.models.info.Info;
import io.swagger.v3.oas.models.servers.Server;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class OpenApiConfig {
    @Bean
    public OpenAPI setOpenAPI() {
        return new OpenAPI()
                .info(new Info()
                        .title("Ads app API Documentation")
                        .version("1.0")
                );
    }
}
