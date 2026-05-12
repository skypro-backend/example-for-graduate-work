package ru.skypro.homework;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;

/**
 * Главный класс приложения "Объявления" (аналог Avito).
 * <p>
 * Запускает Spring Boot-приложение с поддержкой:
 * <ul>
 *   <li>Spring Data JPA — для работы с базой данных</li>
 *   <li>Spring Security — для аутентификации и авторизации</li>
 *   <li>Swagger/OpenAPI — для документирования REST API</li>
 *   <li>MapStruct — для маппинга сущностей и DTO</li>
 * </ul>
 *
 * @see EnableGlobalMethodSecurity — включает аннотации {@code @PreAuthorize}, {@code @PostAuthorize} и др.
 * @see SpringBootApplication — включает автонастройку, сканирование компонентов и конфигурацию
 */
@SpringBootApplication
@EnableGlobalMethodSecurity(prePostEnabled = true)
public class HomeworkApplication {
    /**
     * Точка входа в приложение.
     * <p>
     * Запускает Spring Boot-контекст, загружает все бины, настраивает соединение с БД,
     * запускает встроенный Tomcat и открывает порт (по умолчанию 8080).
     *
     * @param args аргументы командной строки (не используются)
     */
    public static void main(String[] args) {
        SpringApplication.run(HomeworkApplication.class, args);
    }
}