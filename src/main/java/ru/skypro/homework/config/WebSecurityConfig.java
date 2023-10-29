package ru.skypro.homework.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.http.SessionCreationPolicy;

import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;
import static org.springframework.security.config.Customizer.withDefaults;

/**
 * Конфигурационный класс для настройки безопасности веб-приложения.
 */
@Configuration
//@EnableWebSecurity
public class WebSecurityConfig  {

    @Autowired
    private PasswordEncoder passwordEncoder;

    private static final String[] AUTH_WHITELIST = {
            "/swagger-resources/**",
            "/swagger-ui.html",
            "/v3/api-docs",
            "/webjars/**",
            "/login",
            "/register"
    };

    /**
     * Метод создает фильтр цепочки безопасности для настройки правил авторизации и аутентификации HTTP-запросов.
     *
     * @param http Объект HttpSecurity для настройки безопасности.
     * @return Фильтр цепочки безопасности с настроенными правилами.
     * @throws Exception В случае ошибки при настройке безопасности.
     * Вызывая метод csrf(), получчаем доступ к параметрам конфигурации CSRF.
     * Вызов функции disable() отключит защиту CSRF.
     */
    @Bean
    public SecurityFilterChain filterChain(HttpSecurity http) throws Exception {
        http
                .csrf()
                .disable()
                .authorizeHttpRequests(
                        authorization ->
                                authorization
                                        .mvcMatchers(AUTH_WHITELIST)
                                        .permitAll()
                                          .mvcMatchers(HttpMethod.GET, "/ads", "/ads/image/**", "/users/image/**")
                                          .permitAll()
                                        .mvcMatchers("/ads/**", "/users/**")
                                        .authenticated())
                .cors()
                .disable()
                .httpBasic(withDefaults());

        return http.build();
    }


    // Метод создает и настраивает бин для шифрования паролей с использованием BCryptPasswordEncode
    @Bean
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }

}
