package com.skypro.config;

import io.swagger.v3.oas.models.Operation;
import io.swagger.v3.oas.models.media.Content;
import io.swagger.v3.oas.models.media.MediaType;
import io.swagger.v3.oas.models.media.Schema;
import io.swagger.v3.oas.models.parameters.RequestBody;
import org.springdoc.core.customizers.OperationCustomizer;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.method.HandlerMethod;

import java.util.List;

/**
 * Конфигурация для кастомизации Swagger-документации.
 * <p>
 * Добавляет специальную настройку для эндпоинта {@code POST /ads},
 * чтобы Swagger UI правильно отображал поля {@code properties} и {@code image}
 * как multipart/form-data.
 * </p>
 */
@Configuration
public class SwaggerConfig {

    /**
     * Создаёт бин {@link OperationCustomizer}, который модифицирует
     * документацию для метода {@code addAd} в {@code AdController}.
     * <p>
     * Убирает автоматически добавленные параметры и заменяет их
     * на корректное описание multipart-запроса с двумя полями:
     * <ul>
     *   <li>{@code properties} — JSON-строка с заголовком, ценой и описанием</li>
     *   <li>{@code image} — бинарный файл</li>
     * </ul>
     * </p>
     *
     * @return кастомизатор для Swagger операций
     */
    @Bean
    public OperationCustomizer multipartFormCustomizer() {
        return (Operation operation, HandlerMethod handlerMethod) -> {
            if (handlerMethod.getMethod().getName().equals("addAd")) {
                operation.setParameters(null);
                operation.setRequestBody(new RequestBody()
                        .content(new Content()
                                .addMediaType("multipart/form-data",
                                        new MediaType().schema(new Schema<>()
                                                .type("object")
                                                .addProperties("properties",
                                                        new Schema<>()
                                                                .type("string")
                                                                .example("{\"title\":\"Товар\",\"price\":1000,\"description\":\"Описание\"}"))
                                                .addProperties("image",
                                                        new Schema<>()
                                                                .type("string")
                                                                .format("binary"))
                                                .required(List.of("properties", "image"))
                                        ))
                        ));
            }
            return operation;
        };
    }
}
