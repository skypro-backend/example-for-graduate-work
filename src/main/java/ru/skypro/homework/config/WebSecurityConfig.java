package ru.skypro.homework.config;

import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.authentication.dao.DaoAuthenticationProvider;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.util.matcher.AntPathRequestMatcher;
import org.springframework.web.cors.CorsConfiguration;
import org.springframework.web.cors.CorsConfigurationSource;
import org.springframework.web.cors.UrlBasedCorsConfigurationSource;
import ru.skypro.homework.repository.UserRepository;
import ru.skypro.homework.service.CustomUserDetailsService;

/**
 * Конфигурационный класс для настройки безопасности в приложении.
 * В этом классе задаются правила авторизации, настройки CORS, обработка аутентификации.
 */
@Configuration
@RequiredArgsConstructor
@EnableGlobalMethodSecurity(prePostEnabled = true)
public class WebSecurityConfig {

    private final UserRepository userRepository;

    @Bean
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }

    /**
     * Bean для получения данных о пользователе.
     * Использует сервис CustomUserDetailsService, который работает с UserRepository.
     *
     * @return сервис деталей пользователя.
     */
    @Bean
    public UserDetailsService userDetailsService() {
        return new CustomUserDetailsService(userRepository);
    }

    /**
     * Bean для провайдера аутентификации.
     * Использует ранее определенный UserDetailsService и PasswordEncoder.
     *
     * @return провайдер аутентификации.
     */
    @Bean public AuthenticationProvider authenticationProvider() {
        DaoAuthenticationProvider provider = new DaoAuthenticationProvider();
        provider.setUserDetailsService(userDetailsService());
        provider.setPasswordEncoder(passwordEncoder()); return provider;
    }

    /**
     * Настройка CORS для проекта.
     * Позволяет фронтенду и Swagger обращаться к API с любого происхождения.
     *
     * @return источник конфигурации CORS.
     */
    @Bean
    public CorsConfigurationSource corsConfigurationSource() {
        CorsConfiguration configuration = new CorsConfiguration();
        configuration.addAllowedOriginPattern("*");
        configuration.addAllowedMethod("*");
        configuration.addAllowedHeader("*");
        configuration.setAllowCredentials(true);

        UrlBasedCorsConfigurationSource source = new UrlBasedCorsConfigurationSource();
        source.registerCorsConfiguration("/**", configuration);
        return source;
    }

    /**
     * Основная настройка фильтров безопасности.
     * Определяет, какие маршруты доступны без авторизации, а какие требуют авторизации.
     *
     * @param http главный объект конфигурации HttpSecurity.
     * @return настроенное SecurityFilterChain.
     * @throws Exception возможные ошибки при настройке.
     */
    @Bean
    public SecurityFilterChain filterChain(HttpSecurity http) throws Exception {
        http
                .csrf().disable()
                .cors()
                .and()
                .sessionManagement()
                .sessionCreationPolicy(SessionCreationPolicy.STATELESS)
                .and()
                .authorizeHttpRequests(auth -> auth
                        .requestMatchers(
                                new AntPathRequestMatcher("/swagger-ui/**"),
                                new AntPathRequestMatcher("/swagger-ui.html"),
                                new AntPathRequestMatcher("/v3/api-docs/**"),
                                new AntPathRequestMatcher("/swagger-resources/**"),
                                new AntPathRequestMatcher("/webjars/**")
                        ).permitAll()

                        .requestMatchers(
                                new AntPathRequestMatcher("/login"),
                                new AntPathRequestMatcher("/register")
                        ).permitAll()

                        .requestMatchers(
                                new AntPathRequestMatcher("/ads/**", "GET"),
                                new AntPathRequestMatcher("/images/**", "GET")
                        ).permitAll()

                        .anyRequest().authenticated()
                )
                .httpBasic()
                .and()
                .exceptionHandling()
                .authenticationEntryPoint((request, response, authException) ->
                        response.sendError(401, "Unauthorized"));

        return http.build();
    }
}