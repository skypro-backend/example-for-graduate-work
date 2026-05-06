package ru.skypro.homework.config;

import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.Customizer;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.authentication.configuration.AuthenticationConfiguration;
import org.springframework.security.config.annotation.method.configuration.EnableMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.provisioning.InMemoryUserDetailsManager;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.web.cors.CorsConfiguration;
import org.springframework.web.cors.CorsConfigurationSource;
import org.springframework.web.cors.UrlBasedCorsConfigurationSource;
import ru.skypro.homework.entity.Role;
import ru.skypro.homework.security.CustomUserDetailsService;

import java.util.List;

import static org.springframework.security.config.Customizer.withDefaults;

/**
 * Конфигурация безопасности приложения.
 * Определяет:
 * - правила доступа к endpoints,
 * - CORS-настройки для взаимодействия с frontend,
 * - аутентификацию,
 * - шифрование паролей.
 * Разграничение доступа:
 * - публичные endpoints (swagger, login, register, GET /ads/**),
 * - защищённые endpoints (создание/изменение данных),
 * - административные и пользовательские операции.
 */
@EnableMethodSecurity
@RequiredArgsConstructor
@Configuration
public class WebSecurityConfig {

    private final CustomUserDetailsService customUserDetailsService;

    private static final String[] AUTH_WHITELIST = {
            "/swagger-resources/**",
            "/swagger-ui/**",
            "/v3/api-docs/**",
            "/webjars/**",
            "/login",
            "/register"
    };

    /**
     * Основная цепочка фильтров Spring Security.
     * Настраивает:
     * - отключение CSRF для REST API,
     * - разрешение OPTIONS-запросов (CORS preflight),
     * - публичный доступ к GET /ads/**,
     * - защищённый доступ к изменению данных.
     */
    @Bean
    public SecurityFilterChain filterChain(HttpSecurity http) throws Exception {
        http
                .csrf()
                .disable()
                .authorizeHttpRequests(
                        authorization ->
                                authorization
                                        .mvcMatchers(HttpMethod.OPTIONS, "/**")
                                        .permitAll()
                                        .mvcMatchers(AUTH_WHITELIST)
                                        .permitAll()
                                        .mvcMatchers("/ads/me")
                                        .authenticated()
                                        .mvcMatchers(HttpMethod.GET, "/ads/**")
                                        .permitAll()
                                        .mvcMatchers("/ads/**", "/users/**")
                                        .authenticated()
                                        .anyRequest().authenticated())
                .cors(withDefaults())
                .httpBasic(withDefaults());
        return http.build();
    }

    /**
     * Конфигурация CORS (Cross-Origin Resource Sharing).
     * Определяет разрешённый frontend-домен, HTTP-методы и заголовки.
     * Используется для взаимодействия backend API с frontend.
     * Включена поддержка credentials для передачи авторизационных данных.
     */
    @Bean
    public CorsConfigurationSource corsConfigurationSource() {
        CorsConfiguration configuration = new CorsConfiguration();
        configuration.setAllowedOrigins(List.of("http://192.168.99.100:3000", "http://localhost:3000"));
        configuration.setAllowedMethods(List.of("GET", "POST", "PATCH", "DELETE", "OPTIONS"));
        configuration.setAllowedHeaders(List.of("*"));
        configuration.setAllowCredentials(true);
        UrlBasedCorsConfigurationSource source = new UrlBasedCorsConfigurationSource();
        source.registerCorsConfiguration("/**", configuration);
        return source;
    }

    /**
     * Создает и настраивает AuthenticationManager для аутенификации пользователей.
     * Использует {@link CustomUserDetailsService} для загрузки пользователя из базы данных и {@link PasswordEncoder} для проверки пароля.
     * Конфигурируется через {@link AuthenticationManagerBuilder}, который связывает сервис загрузки пользователей и механизм проверки пароля.
     * Используется в процессе логина.
     */
    @Bean
    public AuthenticationManager authenticationManager(HttpSecurity http, PasswordEncoder passwordEncoder, CustomUserDetailsService userDetailsService) throws Exception {
        return http.getSharedObject(AuthenticationManagerBuilder.class)
                .userDetailsService(userDetailsService)
                .passwordEncoder(passwordEncoder)
                .and()
                .build();
    }

    /**
     * Кодировщик паролей BCrypt.
     * Используется для безопасного хранения паролей пользователей в зашифрованном виде.
     */
    @Bean
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }
}
