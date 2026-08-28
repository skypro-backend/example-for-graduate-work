package ru.skypro.homework.config;

import io.swagger.v3.oas.annotations.OpenAPIDefinition;
import io.swagger.v3.oas.annotations.enums.SecuritySchemeIn;
import io.swagger.v3.oas.annotations.enums.SecuritySchemeType;
import io.swagger.v3.oas.annotations.info.Info;
import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import io.swagger.v3.oas.annotations.security.SecurityScheme;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;
import ru.skypro.homework.filter.BasicAuthCorsFilter;

import static org.springframework.security.config.Customizer.withDefaults;


/**
 * Конфигурация безопасности приложения.
 * Основные функции:
 * - настройка доступа к эндпоинтам (разрешения и ограничения);
 * - настройка Basic-аутентификации;
 * - создание бинов для шифрования паролей;
 * - интеграция с кастомным фильтром CORS.
 */

@Configuration
@OpenAPIDefinition(
        info = @Info(
                title = "Ads Platform API",
                version = "1.0",
                description = "API для платформы по перепродаже вещей"
        ),
        security = @SecurityRequirement(name = "basicAuth")
)
@SecurityScheme(
        name = "basicAuth",
        type = SecuritySchemeType.HTTP,
        scheme = "basic",
        in = SecuritySchemeIn.HEADER
)
public class WebSecurityConfig {

    private static final String[] AUTH_WHITELIST = {
            "/swagger-resources/**",
            "/swagger-ui.html",
            "/swagger-ui/**",
            "/swagger-ui/",
            "/swagger-ui/index.html",
            "/v3/api-docs",
            "/v3/api-docs/**",
            "/webjars/**",
            "/login",
            "/register",
            "/images/**"
    };


    private final BasicAuthCorsFilter corsFilter;
    private final UserDetailsService userDetailsService;

    public WebSecurityConfig(BasicAuthCorsFilter corsFilter, UserDetailsService userDetailsService) {
        this.corsFilter = corsFilter;
        this.userDetailsService = userDetailsService;
    }

    /**
     * Настройка цепочки фильтров безопасности.
     * Основные правила:
     * - CSRF отключено (используется Basic Auth);
     * - CORS настроен через кастомный фильтр;
     * - публичные эндпоинты доступны без авторизации;
     * - создание/обновление/удаление объявлений и комментариев доступно только для USER/ADMIN;
     * - просмотр объявлений и комментариев доступен всем.
     * @param http объект {@link HttpSecurity} для настройки
     * @return {@link SecurityFilterChain} с настроенными правилами
     * @throws Exception если ошибка при настройке безопасности
     */
    @Bean
    public SecurityFilterChain filterChain(HttpSecurity http) throws Exception {
        http.csrf().disable()
                .cors().and()
                .addFilterBefore(corsFilter, UsernamePasswordAuthenticationFilter.class)
                .userDetailsService(userDetailsService)
                .authorizeHttpRequests(auth -> auth
                        .antMatchers(AUTH_WHITELIST).permitAll()
                        .antMatchers(org.springframework.http.HttpMethod.GET, "/ads/**").permitAll()
                        .antMatchers(org.springframework.http.HttpMethod.POST, "/ads/**").hasAnyRole("USER", "ADMIN")
                        .antMatchers(org.springframework.http.HttpMethod.PUT, "/ads/**").hasAnyRole("USER", "ADMIN")
                        .antMatchers(org.springframework.http.HttpMethod.PATCH, "/ads/**").hasAnyRole("USER", "ADMIN")
                        .antMatchers(org.springframework.http.HttpMethod.DELETE, "/ads/**").hasAnyRole("USER", "ADMIN")
                        .antMatchers(org.springframework.http.HttpMethod.GET, "/ads/*/comments").permitAll()
                        .antMatchers("/ads/*/comments/**").hasAnyRole("USER", "ADMIN")
                        .antMatchers("/users/**").authenticated()
                        .anyRequest().authenticated()
                )
                .httpBasic(withDefaults());
        return http.build();
    }

    /**
     * Создаёт бин для шифрования паролей.
     * Используется алгоритм BCrypt для хеширования паролей перед сохранением в БД.
     * @return {@link PasswordEncoder} для шифрования паролей
     */
    @Bean
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }
}