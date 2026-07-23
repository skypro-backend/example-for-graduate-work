package ru.skypro.homework.config;

import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.web.cors.CorsConfiguration;
import org.springframework.web.cors.CorsConfigurationSource;
import org.springframework.web.cors.UrlBasedCorsConfigurationSource;

import java.util.List;

@Configuration
@RequiredArgsConstructor
public class WebSecurityConfig {


    private static final String[] AUTH_WHITELIST = {
            "/login",
            "/register",
            "/swagger-ui/**",
            "/v3/api-docs/**"
    };

    @Bean
    public SecurityFilterChain filterChain(
            HttpSecurity http
    ) throws Exception {

        http
                .csrf()
                .disable()

                .cors()

                .and()

                .authorizeHttpRequests(auth -> auth

                        // открытые эндпоинты
                        .antMatchers(AUTH_WHITELIST)
                        .permitAll()


                        // просмотр своих объявлений
                        .antMatchers(
                                HttpMethod.GET,
                                "/ads/me"
                        )
                        .authenticated()


                        // просмотр всех объявлений
                        .antMatchers(
                                HttpMethod.GET,
                                "/ads"
                        )
                        .permitAll()


                        // просмотр конкретного объявления
                        .antMatchers(
                                HttpMethod.GET,
                                "/ads/{id}"
                        )
                        .permitAll()


                        // создание, изменение, удаление объявлений
                        .antMatchers(
                                HttpMethod.POST,
                                "/ads/**",
                                "/comments/**"
                        )
                        .hasAnyRole(
                                "USER",
                                "ADMIN"
                        )


                        .antMatchers(
                                HttpMethod.PUT,
                                "/ads/**",
                                "/comments/**"
                        )
                        .hasAnyRole(
                                "USER",
                                "ADMIN"
                        )
                        .antMatchers(
                                HttpMethod.PATCH,
                                "/ads/**",
                                "/comments/**",
                                "/users/**"
                        )
                        .hasAnyRole(
                                "USER",
                                "ADMIN"
                        )
                        .antMatchers(
                                HttpMethod.DELETE,
                                "/ads/**",
                                "/comments/**"
                        )
                        .hasAnyRole(
                                "USER",
                                "ADMIN"
                        )
                        // работа с профилем пользователя
                        .antMatchers(
                                "/users/**"
                        )
                        .authenticated()

                        .anyRequest()
                        .permitAll()
                )

                .httpBasic();

        return http.build();
    }

    @Bean
    public PasswordEncoder passwordEncoder() {

        return new BCryptPasswordEncoder();
    }

    @Bean
    public CorsConfigurationSource corsConfigurationSource() {

        CorsConfiguration configuration =
                new CorsConfiguration();

        configuration.setAllowedOrigins(
                List.of(
                        "http://localhost:3000"
                )
        );

        configuration.setAllowedMethods(
                List.of(
                        "GET",
                        "POST",
                        "PUT",
                        "PATCH",
                        "DELETE",
                        "OPTIONS"
                )
        );

        configuration.setAllowedHeaders(
                List.of(
                        "*"
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