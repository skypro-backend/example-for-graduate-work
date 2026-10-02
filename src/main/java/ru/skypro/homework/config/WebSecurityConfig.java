package ru.skypro.homework.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;

import static org.springframework.security.config.Customizer.withDefaults;

@Configuration
public class WebSecurityConfig {

    private static final String[] AUTH_WHITELIST = {
            "/swagger-resources/**",
            "/swagger-ui.html",
            "/swagger-ui/**",
            "/v3/api-docs",
            "/v3/api-docs/**",
            "/webjars/**",
            "/login",
            "/register"
    };

    @Bean
    public SecurityFilterChain filterChain(HttpSecurity http) throws Exception {
        http.csrf()
                .disable()
                .authorizeHttpRequests(
                                authorization ->
                                        authorization
                                                .mvcMatchers(AUTH_WHITELIST)
                                                .permitAll()
                                                .mvcMatchers(HttpMethod.OPTIONS, "/**")
                                                .permitAll()
                                                .mvcMatchers(HttpMethod.GET, "/ads")
                                                .permitAll()
                                                .mvcMatchers(HttpMethod.GET, "/ads/*/image")
                                                .permitAll()
                                                .mvcMatchers(HttpMethod.GET, "/users/*/image")
                                                .permitAll()
                                                .mvcMatchers(HttpMethod.POST, "/ads/**")
                                                .hasAnyRole("USER", "ADMIN")
                                                .mvcMatchers(HttpMethod.PATCH, "/ads/**")
                                                .hasAnyRole("USER", "ADMIN")
                                                .mvcMatchers(HttpMethod.DELETE, "/ads/**")
                                                .hasAnyRole("USER", "ADMIN")
                                                .mvcMatchers("/ads/**", "/users/**")
                                                .hasAnyRole("USER", "ADMIN")
                                                .anyRequest()
                                                .authenticated())
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
