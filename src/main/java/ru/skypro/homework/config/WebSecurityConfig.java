package ru.skypro.homework.config;

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

@Configuration
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
            "/register"
    };


    private final BasicAuthCorsFilter corsFilter;
    private final UserDetailsService userDetailsService;

    public WebSecurityConfig(BasicAuthCorsFilter corsFilter, UserDetailsService userDetailsService) {
        this.corsFilter = corsFilter;
        this.userDetailsService = userDetailsService;
    }

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

    @Bean
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }
}