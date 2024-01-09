package ru.skypro.kakavito.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;
import ru.skypro.kakavito.dto.Role;
import ru.skypro.kakavito.repository.UserRepo;
import ru.skypro.kakavito.service.impl.UserDetailsServiceImpl;

import static org.springframework.security.config.Customizer.withDefaults;

/**
 * Класс для настройки WebSecurity
 */
@Configuration
//@EnableWebSecurity
public class WebSecurityConfig {

    /**
     * Создание "Белого" списка авторизации
     */
    private static final String[] AUTH_WHITELIST = {
            "/swagger-resources/**",
            "/swagger-ui.html",
            "/v3/api-docs",
            "/webjars/**"
    };

    private final UserRepo userRepo;

    public WebSecurityConfig(UserRepo userRepo) {
        this.userRepo = userRepo;
    }

    /**
     * Создание @Bean UserDetailServiceImpl для получения имени пользователя,
     * пароля и других атрибутов для проверки подлинности с помощью имени пользователя и пароля.
     *
     * @param passwordEncoder
     */
    @Bean
    public UserDetailsServiceImpl userDetailsService(PasswordEncoder passwordEncoder) {
        return new UserDetailsServiceImpl(userRepo, passwordEncoder);
    }

    /**
     * Создание @Bean SecurityFilterChain для аутентификации, авторизации, проверка роли.
     * Фильтры выполняются в определенном порядке.
     *
     * @param http
     * @return http
     * @throws Exception
     */
    @Bean
    public SecurityFilterChain filterChain(HttpSecurity http) throws Exception {
        http.csrf().disable().authorizeHttpRequests(httpRequest -> httpRequest
                .mvcMatchers(AUTH_WHITELIST).permitAll()
                .mvcMatchers(HttpMethod.GET, "/ads").permitAll()
                .mvcMatchers(HttpMethod.POST, "/login", "/register").permitAll()
                .mvcMatchers(HttpMethod.POST, "/ads").hasAnyRole(Role.USER.name(), Role.ADMIN.name())
                .mvcMatchers("/ads/**").hasAnyRole(Role.USER.name(), Role.ADMIN.name())
                .mvcMatchers("/users/**").hasAnyRole(Role.USER.name(), Role.ADMIN.name())
        ).cors().and().httpBasic(withDefaults());
        return http.build();
    }

    /**
     * Создание @Bean PasswordEncoder для безопасного хранения паролей пользователей
     *
     * @return BCryptPasswordEncoder
     */
    @Bean
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }

}
