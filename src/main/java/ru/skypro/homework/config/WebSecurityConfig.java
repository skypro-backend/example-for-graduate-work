package ru.skypro.homework.config;

import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.authentication.dao.DaoAuthenticationProvider;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.web.cors.CorsConfiguration;
import org.springframework.web.cors.CorsConfigurationSource;
import org.springframework.web.cors.UrlBasedCorsConfigurationSource;
import ru.skypro.homework.service.impl.UserDetailsServiceImpl;

import java.util.Arrays;

/**
 * Конфигурация безопасности приложения.
 * <p>
 * Настраивает:
 * <ul>
 *   <li>Аутентификацию через базу данных (UserDetailsService)</li>
 *   <li>Авторизацию URL-путей</li>
 *   <li>CORS политики для фронтенда (http://localhost:3000)</li>
 *   <li>HTTP Basic аутентификацию</li>
 *   <li>Исключения при ошибках доступа</li>
 * </ul>
 */
@Configuration
@EnableWebSecurity
@RequiredArgsConstructor
public class WebSecurityConfig {

    /**
     * Список путей, доступных без аутентификации.
     * <p>
     * Включает:
     * <ul>
     *   <li>Swagger UI и API документацию</li>
     *   <li>Страницу логина и регистрации</li>
     *   <li>Получение всех объявлений ({@code GET /ads})</li>
     *   <li>Доступ к изображениям ({@code /image/**})</li>
     * </ul>
     */
    private static final String[] AUTH_WHITELIST = {"/swagger-resources/**", "/swagger-ui.html", "/v3/api-docs", "/webjars/**", "/h2-console/**", "/login", "/register", "/ads", "/ads/{id}", "/image/**",};

    private final UserDetailsServiceImpl userDetailsService;

    /**
     * Основная цепочка фильтров безопасности.
     * <p>
     * Настройки:
     * <ul>
     *   <li>Отключён CSRF (т.к. используем stateless аутентификацию)</li>
     *   <li>Включён CORS с настройками из {@link #corsConfigurationSource()}</li>
     *   <li>Разрешён доступ к путям из {@link #AUTH_WHITELIST}</li>
     *   <li>Для {@code /ads} разрешён GET без авторизации</li>
     *   <li>Остальные защищённые пути требуют ROLE_USER или ROLE_ADMIN</li>
     *   <li>Используется HTTP Basic аутентификация</li>
     *   <li>Кастомные обработчики ошибок: 401 (неавторизован), 403 (доступ запрещён)</li>
     *   <li>Отключены заголовки безопасности для H2 Console</li>
     * </ul>
     *
     * @param http основной объект конфигурации безопасности
     * @return настроенная цепочка фильтров
     * @throws Exception если возникает ошибка при настройке
     */
    @Bean
    public SecurityFilterChain filterChain(HttpSecurity http) throws Exception {
        http.cors().and().csrf().disable().authorizeHttpRequests(auth -> auth.antMatchers(AUTH_WHITELIST).permitAll().antMatchers(HttpMethod.GET, "/ads").permitAll().antMatchers("/ads/**", "/users/**", "/comments/**").hasAnyAuthority("ROLE_USER", "ROLE_ADMIN")).httpBasic().and().exceptionHandling(ex -> ex.accessDeniedHandler((request, response, accessDeniedException) -> response.setStatus(403)).authenticationEntryPoint((request, response, authException) -> response.setStatus(401))).headers(headers -> headers.frameOptions().disable());

        return http.build();
    }

    /**
     * Настройка CORS (Cross-Origin Resource Sharing).
     * <p>
     * Разрешает запросы с любого origin (в dev), все HTTP-методы и заголовки.
     * Поддерживает учётные данные (cookies, авторизация).
     *
     * @return источник конфигурации CORS
     */
    @Bean
    public CorsConfigurationSource corsConfigurationSource() {
        CorsConfiguration configuration = new CorsConfiguration();
        configuration.setAllowedOriginPatterns(Arrays.asList("*"));
        configuration.setAllowedMethods(Arrays.asList("GET", "POST", "PUT", "PATCH", "DELETE", "OPTIONS"));
        configuration.setAllowedHeaders(Arrays.asList("*"));
        configuration.setAllowCredentials(true);
        UrlBasedCorsConfigurationSource source = new UrlBasedCorsConfigurationSource();
        source.registerCorsConfiguration("/**", configuration);
        return source;
    }

    /**
     * Бин для шифрования паролей.
     * <p>
     * Использует алгоритм BCrypt — стандарт для хранения паролей.
     *
     * @return экземпляр {@link PasswordEncoder}
     */
    @Bean
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }

    /**
     * Поставщик аутентификации на основе DAO.
     * <p>
     * Использует реализацию {@link UserDetailsServiceImpl} для загрузки пользователя по email
     * и сравнивает пароль с использованием BCrypt.
     *
     * @return настроенный провайдер аутентификации
     */
    @Bean
    public DaoAuthenticationProvider daoAuthenticationProvider() {
        DaoAuthenticationProvider provider = new DaoAuthenticationProvider();
        provider.setUserDetailsService(userDetailsService);
        provider.setPasswordEncoder(passwordEncoder());
        return provider;
    }
}