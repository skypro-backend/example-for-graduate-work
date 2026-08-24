package ru.skypro.homework.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.provisioning.JdbcUserDetailsManager;
import org.springframework.security.web.SecurityFilterChain;

import javax.sql.DataSource;

import static org.springframework.security.config.Customizer.withDefaults;

@Configuration
@EnableWebSecurity
@EnableGlobalMethodSecurity(prePostEnabled = true)
public class WebSecurityConfig {

    private static final String[] AUTH_WHITELIST = {
            "/swagger-resources/**",
            "/swagger-ui.html",
            "/v3/api-docs",
            "/webjars/**",
            "/login",
            "/register"
    };

    // Публичные GET запросы для объявлений и изображений
    private static final String[] PUBLIC_GET_ENDPOINTS = {
            "/ads",
            "/ads/{id}",
            "/images/**"
    };

    @Bean
    public UserDetailsService userDetailsService(DataSource dataSource) {
        JdbcUserDetailsManager manager = new JdbcUserDetailsManager(dataSource);
        manager.setUsersByUsernameQuery(
                "SELECT username, password, true FROM users WHERE username = ?"
        );
        manager.setAuthoritiesByUsernameQuery(
                "SELECT username, 'ROLE_' || role FROM users WHERE username = ?"
        );
        return manager;
    }

    @Bean
    public SecurityFilterChain filterChain(HttpSecurity http) throws Exception {
        http
                .csrf()
                .disable()
                .sessionManagement()
                .sessionCreationPolicy(SessionCreationPolicy.STATELESS)
                .and()
                .authorizeHttpRequests(
                        authorization ->
                                authorization
                                        // Swagger и регистрация - доступны всем
                                        .mvcMatchers(AUTH_WHITELIST)
                                        .permitAll()
                                        // GET запросы на объявления и изображения - доступны всем
                                        .mvcMatchers(org.springframework.http.HttpMethod.GET, "/ads")
                                        .permitAll()
                                        .mvcMatchers(org.springframework.http.HttpMethod.GET, "/ads/{id}")
                                        .permitAll()
                                        .mvcMatchers(org.springframework.http.HttpMethod.GET, "/images/**")
                                        .permitAll()
                                        // Остальные запросы к /ads/** требуют аутентификации
                                        .mvcMatchers("/ads/**")
                                        .hasAnyRole("USER", "ADMIN")
                                        // Запросы к /users/** требуют аутентификации
                                        .mvcMatchers("/users/**")
                                        .hasAnyRole("USER", "ADMIN")
                                        // Все остальные запросы требуют аутентификации
                                        .anyRequest()
                                        .authenticated()
                )
                .cors()
                .and()
                .httpBasic(withDefaults());

        return http.build();
    }

    @Bean
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }
}