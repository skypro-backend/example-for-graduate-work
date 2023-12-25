package ru.skypro.homework.config;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;

import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;
import ru.skypro.homework.dto.Role;
import ru.skypro.homework.repo.UserRepo;
import ru.skypro.homework.util.CustomUserDetailsService;



@Configuration
@EnableWebSecurity
public class WebSecurityConfig {
    private static final Logger logger = LoggerFactory.getLogger(WebSecurityConfig.class);
    private static final String[] AUTH_WHITELIST = {
            "/swagger-resources/**",
            "/swagger-ui.html",
            "/v3/api-docs",
            "/webjars/**"
    };
    private final UserRepo userRepo;

    public WebSecurityConfig(UserRepo userRepo){
        this.userRepo = userRepo;
    }

    @Bean
    public CustomUserDetailsService userDetailsService(PasswordEncoder passwordEncoder) {
        return new CustomUserDetailsService(userRepo, passwordEncoder);
    }

    @Bean
    public SecurityFilterChain filterChain(HttpSecurity http) throws Exception {
         http.csrf().disable().authorizeHttpRequests(httpRequest -> httpRequest
                        .mvcMatchers(AUTH_WHITELIST).permitAll()
                        .mvcMatchers(HttpMethod.GET, "/ads").permitAll()
                        .mvcMatchers(HttpMethod.POST, "/login").permitAll()
                        .mvcMatchers(HttpMethod.POST, "/register").permitAll()
                        .mvcMatchers(HttpMethod.POST,"/ads").hasAnyRole(Role.USER.name(), Role.ADMIN.name())
                        .mvcMatchers("/ads/**").hasAnyRole(Role.USER.name(), Role.ADMIN.name())
                        .mvcMatchers("/users/**").hasAnyRole(Role.USER.name(), Role.ADMIN.name())
                );
        return http.build();
    }

    @Bean
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }

    @Bean
    public Authentication authentication(){
        return SecurityContextHolder.getContext().getAuthentication();
    }

}
