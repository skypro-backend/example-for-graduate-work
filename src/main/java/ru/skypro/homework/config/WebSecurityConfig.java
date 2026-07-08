package ru.skypro.homework.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.config.Customizer;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.util.matcher.AntPathRequestMatcher;
import org.springframework.security.web.util.matcher.RequestMatcher;
import ru.skypro.homework.service.CustomUserDetailsService;

@Configuration
public class WebSecurityConfig {
    private final CustomUserDetailsService customUserDetailsService;

    public WebSecurityConfig(CustomUserDetailsService customUserDetailsService) {
        this.customUserDetailsService = customUserDetailsService;
    }

    @Bean
    public SecurityFilterChain filterChain(HttpSecurity http) throws Exception {
        http.csrf().disable()
                .authorizeHttpRequests(auth -> auth
                      .requestMatchers(antMapper("/swagger-ui/**")).permitAll()
                        .requestMatchers(antMapper("/v3/api-docs/**")).permitAll()
                        .requestMatchers(antMapper("/swagger-resources/**")).permitAll()
                        .requestMatchers(antMapper("/webjars/**")).permitAll()

                        .requestMatchers(antMapper("/login")).permitAll()
                        .requestMatchers(antMapper("/register")).permitAll()

                        .requestMatchers(antMapper(HttpMethod.GET, "/ads")).permitAll()
                   //     .requestMatchers(antMapper("/ads/**")).authenticated()

                        .anyRequest().permitAll())
                .cors().and()
      //          .userDetailsService(customUserDetailsService)
                .httpBasic(Customizer.withDefaults());

        return http.build();
    }

    private static RequestMatcher antMapper(HttpMethod method, String path) {
        return new AntPathRequestMatcher(path, method.name());
    }

    private static AntPathRequestMatcher antMapper(String path) {
        return new AntPathRequestMatcher(path);
    }

    @Bean("passwordEncoder")
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }
}