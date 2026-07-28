package ru.skypro.homework.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.config.Customizer;
import org.springframework.security.config.annotation.method.configuration.EnableMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.web.cors.CorsConfiguration;
import org.springframework.web.cors.CorsConfigurationSource;
import org.springframework.web.cors.UrlBasedCorsConfigurationSource;

import java.util.List;

@Configuration
@EnableMethodSecurity
public class SecurityConfig {

    private static final String[] PUBLIC_ENDPOINTS = {
            "/login",
            "/register",

            "/swagger-ui/**",
            "/swagger-ui.html",
            "/v3/api-docs/**",
            "/swagger-resources/**",
            "/webjars/**"
    };

    private static final String FRONTEND_ORIGIN = "http://localhost:3000";

    @Bean
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }

    @Bean
    public CorsConfigurationSource corsConfigurationSource() {

        CorsConfiguration configuration = new CorsConfiguration();

        configuration.setAllowedOrigins(List.of(FRONTEND_ORIGIN));
        configuration.setAllowedMethods(
                List.of("GET", "POST", "PATCH", "PUT", "DELETE", "OPTIONS")
        );
        configuration.setAllowedHeaders(List.of("*"));
        configuration.setAllowCredentials(true);

        UrlBasedCorsConfigurationSource source =
                new UrlBasedCorsConfigurationSource();

        source.registerCorsConfiguration("/**", configuration);

        return source;
    }

    @Bean
    public SecurityFilterChain filterChain(
            HttpSecurity http,
            CorsConfigurationSource corsConfigurationSource
    ) throws Exception {

        http
                .csrf(csrf -> csrf.disable())

                .cors(cors -> cors.configurationSource(corsConfigurationSource))

                .sessionManagement(session ->
                        session.sessionCreationPolicy(SessionCreationPolicy.STATELESS))

                .authorizeHttpRequests(auth -> auth

                        // публичные ресурсы
                        .requestMatchers(PUBLIC_ENDPOINTS).permitAll()

                        // публичные GET согласно OpenAPI
                        .requestMatchers(HttpMethod.GET, "/ads").permitAll()
                        .requestMatchers(HttpMethod.GET, "/ads/*").permitAll()
                        .requestMatchers(HttpMethod.GET, "/ads/*/comments").permitAll()

                        // изменение объявлений
                        .requestMatchers(
                                HttpMethod.POST,
                                "/ads/**"
                        ).hasAnyRole("USER", "ADMIN")

                        .requestMatchers(
                                HttpMethod.PATCH,
                                "/ads/**"
                        ).hasAnyRole("USER", "ADMIN")

                        .requestMatchers(
                                HttpMethod.DELETE,
                                "/ads/**"
                        ).hasAnyRole("USER", "ADMIN")

                        // пользователи
                        .requestMatchers("/users/**")
                        .hasAnyRole("USER", "ADMIN")

                        .anyRequest().authenticated()
                )

                .httpBasic(Customizer.withDefaults());

        return http.build();
    }
}