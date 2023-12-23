package ru.skypro.homework.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.method.configuration.EnableMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.provisioning.InMemoryUserDetailsManager;
import org.springframework.security.web.SecurityFilterChain;
import ru.skypro.homework.role.Role;

import static org.springframework.security.config.Customizer.withDefaults;

@EnableMethodSecurity
@Configuration
public class WebSecurityConfig {

    // переменная с адресами страниц, которые открываются без аутентификации.
    private static final String[] AUTH_WHITELIST = {
            "/swagger-resources/**",
            "/swagger-ui.html",
            "/v3/api-docs",
            "/webjars/**",
            "/login",
            "/register",
            "/ads",
            "/image/**"
    };

    @Bean
    public SecurityFilterChain filterChain(HttpSecurity http) throws Exception {
        http.csrf()                                                                   // Отключение CSRF-защиты
                .disable()
                .authorizeHttpRequests( authorization -> authorization                  // Настройка правил авторизации для различных запросов
                                        .mvcMatchers(AUTH_WHITELIST)                    // Разрешение запросов из белого списка без аутентификации
                                        .permitAll()
                                        .mvcMatchers("/ads/**", "/users/**") // Требование аутентификации для запросов к "/ads/**" и "/users/**"
                                        .authenticated())
                .cors()                                                                 // Включение настроек Cross-Origin Resource Sharing (CORS)
                .and()
                .httpBasic(withDefaults());                                             // Включение HTTP Basic Authentication с использованием стандартных настроек
        return http.build();                                                            // Возврат экземпляра SecurityFilterChain для обработки запросов безопасности
    }

//    http.csrf().disable(): Отключает CSRF-защиту. CSRF-защита предотвращает атаки, при которых злоумышленник отправляет запросы от имени аутентифицированного пользователя.
//
//            authorization.mvcMatchers(AUTH_WHITELIST).permitAll(): Разрешает запросы, указанные в AUTH_WHITELIST, без проверки аутентификации. AUTH_WHITELIST - это список URL-адресов, которые не требуют аутентификации.
//
//            authorization.mvcMatchers("/ads/**", "/users/**").authenticated(): Требует аутентификации для запросов, начинающихся с "/ads/" и "/users/**". Таким образом, доступ к этим ресурсам требует наличие аутентифицированного пользователя.
//
//            http.cors().and(): Включает настройки Cross-Origin Resource Sharing (CORS), что позволяет указывать, какие домены могут выполнять запросы к вашему серверу.
//
//            http.httpBasic(withDefaults()): Включает HTTP Basic Authentication с использованием стандартных настроек. Basic Authentication требует, чтобы клиент предоставил имя пользователя и пароль для доступа к ресурсам.
//
//            http.build(): Собирает все настройки и возвращает экземпляр SecurityFilterChain для обработки запросов безопасности.
//
    @Bean
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }

}
