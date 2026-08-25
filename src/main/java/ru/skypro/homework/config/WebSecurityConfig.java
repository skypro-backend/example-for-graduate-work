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

/**
 * Конфигурация безопасности приложения.
 * Настраивает аутентификацию, авторизацию и права доступа к ресурсам.
 * Использует Spring Security и JdbcUserDetailsManager для работы с пользователями в БД.
 */
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

    private static final String[] PUBLIC_GET_ENDPOINTS = {
            "/ads",
            "/ads/{id}",
            "/images/**"
    };

    /**
     * Настраивает UserDetailsService для загрузки пользователей из базы данных.
     * Использует JdbcUserDetailsManager с кастомными SQL-запросами.
     *
     * @param dataSource источник данных для подключения к БД
     * @return настроенный UserDetailsService
     */
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

    /**
     * Настраивает цепочку фильтров безопасности.
     * Определяет, какие запросы требуют аутентификации, а какие доступны публично.
     *
     * @param http объект HttpSecurity для настройки
     * @return настроенный SecurityFilterChain
     * @throws Exception если произошла ошибка конфигурации
     */
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
                                        .mvcMatchers(AUTH_WHITELIST)
                                        .permitAll()
                                        .mvcMatchers(org.springframework.http.HttpMethod.GET, "/ads")
                                        .permitAll()
                                        .mvcMatchers(org.springframework.http.HttpMethod.GET, "/ads/{id}")
                                        .permitAll()
                                        .mvcMatchers(org.springframework.http.HttpMethod.GET, "/images/**")
                                        .permitAll()
                                        .mvcMatchers("/ads/**")
                                        .hasAnyRole("USER", "ADMIN")
                                        .mvcMatchers("/users/**")
                                        .hasAnyRole("USER", "ADMIN")
                                        .anyRequest()
                                        .authenticated()
                )
                .cors()
                .and()
                .httpBasic(withDefaults());

        return http.build();
    }

    /**
     * Создает бин для кодирования паролей.
     * Использует BCrypt для безопасного хеширования паролей.
     *
     * @return экземпляр PasswordEncoder
     */
    @Bean
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }
}