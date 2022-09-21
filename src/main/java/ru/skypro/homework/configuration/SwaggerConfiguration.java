package ru.skypro.homework.configuration;

import io.swagger.v3.oas.models.OpenAPI;
import io.swagger.v3.oas.models.info.Contact;
import io.swagger.v3.oas.models.info.Info;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class SwaggerConfiguration {

    @Bean
    public OpenAPI customOpenAPI() {
        return new OpenAPI()
                .info(
                        new Info()
                                .title("ADS-ONLINE Bot API")
                                .version("0.1.0")
                                .contact(
                                        new Contact()
                                                .url("https://github.com/m210/skypro-for-graduate-work")
                                                .name("WIP-Team")
                                )
                );
    }

}
