package com.skypro.config;

import io.swagger.v3.oas.annotations.OpenAPIDefinition;
import io.swagger.v3.oas.annotations.info.Info;

/**
 * Конфигурация для генерации OpenAPI (Swagger) документации.
 * <p>
 * Задаёт общую информацию об API: название, версию и описание.
 * Эта информация отображается на главной странице Swagger UI.
 * </p>
 */
@OpenAPIDefinition(info = @Info(title = "Ads API", version = "1.0", description = "API for managing ads and comments"))
public class OpenAPIConfig {
}
