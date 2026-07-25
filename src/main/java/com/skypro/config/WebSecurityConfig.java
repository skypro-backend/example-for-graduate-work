package com.skypro.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.web.cors.CorsConfiguration;
import org.springframework.web.cors.CorsConfigurationSource;
import org.springframework.web.cors.UrlBasedCorsConfigurationSource;

import java.util.Arrays;

import static org.springframework.security.config.Customizer.withDefaults;

/**
 * Конфигурация безопасности Spring Security.
 * <p>
 * Настраивает:
 * <ul>
 *   <li>Публичный доступ к Swagger UI, страницам входа/регистрации и картинкам</li>
 *   <li>Доступ без авторизации к GET-запросам на получение объявлений</li>
 *   <li>Обязательную аутентификацию для всех остальных запросов к {@code /ads/**} и {@code /users/**}</li>
 *   <li>Basic-аутентификацию с использованием BCrypt для хеширования паролей</li>
 *   <li>CORS-настройки для фронтенда, работающего на порту 3000</li>
 * </ul>
 * </p>
 * <p>
 * Также включает поддержку аннотации {@code @PreAuthorize} для проверки прав
 * в сервисном слое.
 * </p>
 */
@Configuration
@EnableGlobalMethodSecurity(prePostEnabled = true)
public class WebSecurityConfig {

    /**
     * Список URL-путей, доступных без аутентификации.
     */
    private static final String[] AUTH_WHITELIST = {
            "/swagger-resources/**",
            "/swagger-ui.html",
            "/v3/api-docs",
            "/webjars/**",
            "/login",
            "/register",
            "/images/**"
    };

    /**
     * Настраивает цепочку фильтров безопасности.
     * <p>
     * Отключает CSRF (для API), разрешает доступ к whitelist-адресам,
     * разрешает GET-запросы к {@code /ads} и {@code /ads/{id}} без авторизации,
     * требует аутентификации для остальных запросов к {@code /ads/**} и {@code /users/**},
     * включает CORS и Basic-аутентификацию.
     * </p>
     *
     * @param http объект {@link HttpSecurity} для настройки
     * @return настроенная цепочка фильтров
     * @throws Exception если возникает ошибка при настройке
     */
    @Bean
    public SecurityFilterChain filterChain(HttpSecurity http) throws Exception {
        http.csrf()
                .disable()
                .authorizeHttpRequests(
                        authorization ->
                                authorization
                                        .mvcMatchers(HttpMethod.GET, "/ads", "/ads/{id}")
                                        .permitAll()
                                        .mvcMatchers(AUTH_WHITELIST)
                                        .permitAll()
                                        .mvcMatchers("/ads/**", "/users/**")
                                        .authenticated())
                .cors()
                .and()
                .httpBasic(withDefaults());
        return http.build();
    }

    /**
     * Создаёт бин кодировщика паролей с использованием BCrypt.
     *
     * @return экземпляр {@link BCryptPasswordEncoder}
     */
    @Bean
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }

    /**
     * Настраивает CORS (Cross-Origin Resource Sharing) для фронтенда.
     * <p>
     * Разрешает запросы с источника {@code http://localhost:3000},
     * поддерживает методы GET, POST, PATCH, DELETE, OPTIONS,
     * разрешает любые заголовки и разрешает передачу учётных данных.
     * </p>
     *
     * @return источник конфигурации CORS
     */
    @Bean
    public CorsConfigurationSource corsConfigurationSource() {
        CorsConfiguration configuration = new CorsConfiguration();
        configuration.setAllowedOrigins(Arrays.asList("http://localhost:3000"));
        configuration.setAllowedMethods(Arrays.asList("GET", "POST", "PATCH", "DELETE", "OPTIONS"));
        configuration.setAllowedHeaders(Arrays.asList("*"));
        configuration.setAllowCredentials(true);
        UrlBasedCorsConfigurationSource source = new UrlBasedCorsConfigurationSource();
        source.registerCorsConfiguration("/**", configuration);
        return source;
    }
}
