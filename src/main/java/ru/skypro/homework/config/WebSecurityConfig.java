package ru.skypro.homework.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.dao.DaoAuthenticationProvider;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.authentication.configuration.AuthenticationConfiguration;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configurers.AuthorizeHttpRequestsConfigurer;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.util.matcher.AntPathRequestMatcher;

import static org.springframework.security.config.Customizer.withDefaults;

@Configuration
@EnableGlobalMethodSecurity(
        prePostEnabled = true,
        securedEnabled = true,
        jsr250Enabled = true)
public class WebSecurityConfig {


    private static final String[] AUTH_WHITELIST = {
            "/swagger-resources/**",
            "/swagger-ui.html",
            "/v3/api-docs",
            "/webjars/**",
            "/login",
            "/register",
            "/ads"
    };


    @Bean
    public SecurityFilterChain filterChain(HttpSecurity http) throws Exception {
        http
                .csrf().disable()
                .cors().disable()
                .authorizeHttpRequests(
                        authorization ->
                                authorization
                                        .mvcMatchers(AUTH_WHITELIST)
                                        .permitAll()
                                        .mvcMatchers("/ads/**", "/users/**")
                                        .hasRole("USER"))
                .cors()
                .and()
                .httpBasic(withDefaults());
        return http.build();
    }


//    // Создаем бин SecurityFilterChain для настройки фильтра безопасности.
//    @Bean
//    public SecurityFilterChain filterChain(HttpSecurity http) throws Exception {
//        http.csrf()
//                .disable()
//                .authorizeHttpRequests(this::customizeRequest);
//
//        return http.build();
//    }
//
//    // Метод для настройки прав доступа к URL на основе ролей пользователей
//    private void customizeRequest(AuthorizeHttpRequestsConfigurer<HttpSecurity>
//                                          .AuthorizationManagerRequestMatcherRegistry registry) {
//        try {
//            registry.requestMatchers(new AntPathRequestMatcher("/admin/**"))
//                    .hasAnyRole("ADMIN")  // Только для пользователей с ролью ADMIN.
//                    .mvcMatchers(AUTH_WHITELIST)
//                    .permitAll()
//                    .requestMatchers(new AntPathRequestMatcher("/**"))
//                    .hasAnyRole("USER")   // Только для пользователей с ролью USER.
//                    .and()
//                    .formLogin().permitAll()  // Разрешаем всем доступ к форме ввода.
//                    .and()
//                    .logout().logoutUrl("/logout");  // Устанавливаем URL
//            // для выхода из системы.
//
//        } catch (Exception e) {
//            throw new RuntimeException(e);
//        }
//    }

    @Bean
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }

}
