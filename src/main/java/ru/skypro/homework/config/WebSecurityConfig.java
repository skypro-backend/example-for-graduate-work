package ru.skypro.homework.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.dao.DaoAuthenticationProvider;
import org.springframework.security.config.annotation.authentication.configuration.AuthenticationConfiguration;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.web.cors.CorsConfiguration;
import org.springframework.web.cors.CorsConfigurationSource;
import org.springframework.web.cors.UrlBasedCorsConfigurationSource;

import java.util.List;

/**
 * Конфигурация Spring Security.
 *
 * <p>Приложение использует HTTP Basic Authentication.
 * Пользователи загружаются из PostgreSQL.</p>
 */
@Configuration
@EnableGlobalMethodSecurity(prePostEnabled = true)
public class WebSecurityConfig {

    /**
     * Кодировщик паролей BCrypt.
     *
     * @return кодировщик паролей
     */
    @Bean
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }

    /**
     * Провайдер аутентификации.
     *
     * @param userDetailsService сервис загрузки пользователей
     * @param passwordEncoder кодировщик паролей
     * @return провайдер аутентификации
     */
    @Bean
    public DaoAuthenticationProvider authenticationProvider(
            UserDetailsService userDetailsService,
            PasswordEncoder passwordEncoder
    ) {
        DaoAuthenticationProvider provider =
                new DaoAuthenticationProvider();

        provider.setUserDetailsService(userDetailsService);
        provider.setPasswordEncoder(passwordEncoder);

        return provider;
    }

    /**
     * Настраивает правила доступа к REST API.
     *
     * @param http настройки HTTP Security
     * @param authenticationProvider провайдер аутентификации
     * @return цепочка фильтров безопасности
     * @throws Exception при ошибке конфигурации
     */
    @Bean
    public SecurityFilterChain securityFilterChain(
            HttpSecurity http,
            DaoAuthenticationProvider authenticationProvider
    ) throws Exception {

        http
                .csrf().disable()
                .cors().and()

                .sessionManagement()
                .sessionCreationPolicy(
                        SessionCreationPolicy.STATELESS
                )
                .and()

                .authenticationProvider(
                        authenticationProvider
                )

                .authorizeRequests()

                // Регистрация и вход доступны всем.
                .antMatchers(
                        "/register",
                        "/login"
                ).permitAll()

                // Swagger доступен без авторизации.
                .antMatchers(
                        "/swagger-ui.html",
                        "/swagger-ui/**",
                        "/v3/api-docs/**"
                ).permitAll()

                // Просмотр всех объявлений доступен всем.
                .antMatchers(
                        HttpMethod.GET,
                        "/ads"
                ).permitAll()

                // Просмотр одного объявления доступен всем.
                // Подходит только числовой id.
                .antMatchers(
                        HttpMethod.GET,
                        "/ads/{id:[0-9]+}"
                ).permitAll()

                // Изображения доступны без авторизации.
                .antMatchers(
                        HttpMethod.GET,
                        "/users/*/image",
                        "/ads/*/image"
                ).permitAll()

                // Остальные запросы требуют авторизации.
                .anyRequest()
                .authenticated()
                .and()

                .httpBasic();

        return http.build();
    }

    /**
     * Создаёт менеджер аутентификации.
     *
     * @param configuration конфигурация безопасности
     * @return менеджер аутентификации
     * @throws Exception при ошибке создания
     */
    @Bean
    public AuthenticationManager authenticationManager(
            AuthenticationConfiguration configuration
    ) throws Exception {
        return configuration.getAuthenticationManager();
    }

    /**
     * Настраивает CORS для frontend.
     *
     * @return конфигурация CORS
     */
    @Bean
    public CorsConfigurationSource corsConfigurationSource() {
        CorsConfiguration configuration =
                new CorsConfiguration();

        configuration.setAllowedOrigins(
                List.of("http://localhost:3000")
        );

        configuration.setAllowedMethods(
                List.of(
                        "GET",
                        "POST",
                        "PATCH",
                        "DELETE",
                        "OPTIONS"
                )
        );

        configuration.setAllowedHeaders(
                List.of("*")
        );

        configuration.setExposedHeaders(
                List.of(
                        "Authorization",
                        "Content-Type"
                )
        );

        configuration.setAllowCredentials(true);

        UrlBasedCorsConfigurationSource source =
                new UrlBasedCorsConfigurationSource();

        source.registerCorsConfiguration(
                "/**",
                configuration
        );

        return source;
    }
}
