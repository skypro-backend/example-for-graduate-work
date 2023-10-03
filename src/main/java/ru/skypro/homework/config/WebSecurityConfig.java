package ru.skypro.homework.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configurers.AbstractHttpConfigurer;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;

import static org.springframework.security.config.Customizer.withDefaults;
@Configuration
public class WebSecurityConfig {
    @Bean
    public SecurityFilterChain filterChain(HttpSecurity http) throws Exception {
          http.csrf(AbstractHttpConfigurer::disable)
                    .formLogin(formLogin -> formLogin.loginProcessingUrl("/login"))
                    .logout(logout -> logout.logoutUrl("/logout"))
                    .authorizeHttpRequests(
                            authorization ->
                                    authorization
                                            .mvcMatchers(HttpMethod.GET, "/ads")
                                            .permitAll()
                                            .mvcMatchers(HttpMethod.DELETE, "ads/{id}", "ads/{adId}/comments/{commentId}")
                                            .hasRole("ADMIN")
                                            .mvcMatchers(HttpMethod.PATCH, "ads/{id}", "ads/{adId}/comments/{commentId}")
                                            .hasRole("ADMIN")
                                            .mvcMatchers("/ads/**", "/users/**")
                                            .hasRole("USER"))
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
