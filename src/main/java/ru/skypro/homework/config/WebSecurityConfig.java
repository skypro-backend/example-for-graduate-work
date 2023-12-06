package ru.skypro.homework.config;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.jdbc.DataSourceBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.provisioning.InMemoryUserDetailsManager;
import org.springframework.security.provisioning.JdbcUserDetailsManager;
import org.springframework.security.provisioning.UserDetailsManager;
import org.springframework.security.web.SecurityFilterChain;
import ru.skypro.homework.dto.RoleDTO;

import javax.sql.DataSource;

import static org.springframework.security.config.Customizer.withDefaults;

@Configuration
public class WebSecurityConfig {
    @Value("${spring.datasource.url}")
    private String jdbcUrl;
    @Value("${spring.datasource.username}")
    private String jdbcUsername;

    @Value("${spring.datasource.password}")
    private String jdbcPassword;
    @Value("${spring.datasource.driver-class-name}")
    private String jdbcDriver;

    private static final String[] AUTH_WHITELIST = {
            "/swagger-resources/**",
            "/swagger-ui.html",
            "/v3/api-docs",
            "/webjars/**",
            "/login",
            "/register"
    };

    //    @Bean
//    public InMemoryUserDetailsManager userDetailsService(PasswordEncoder passwordEncoder) {
//        UserDetails user =
//                User.builder()
//                        .username("user@gmail.com")
//                        .password("password")
//                        .passwordEncoder(passwordEncoder::encode)
//                        .roles(RoleDTO.USER.name())
//                        .build();
//        return new InMemoryUserDetailsManager(user);
//    }
    @Bean
    DataSource dataSource() {
        DataSourceBuilder<?> dataSourceBuilder = DataSourceBuilder.create();
        dataSourceBuilder.url(jdbcUrl);
        dataSourceBuilder.username(jdbcUsername);
        dataSourceBuilder.password(jdbcPassword);
        dataSourceBuilder.driverClassName(jdbcDriver);
        return dataSourceBuilder.build();
    }

/*
    @Bean
    UserDetailsManager jdbcUserDetailsManager() {
        JdbcUserDetailsManager users = new JdbcUserDetailsManager(dataSource());
        return users;
    }
*/

    @Bean
    public SecurityFilterChain filterChain(HttpSecurity http) throws Exception {
        http.csrf()
                .disable()
                .authorizeHttpRequests(
                        authorization ->
                                authorization
                                        .mvcMatchers(AUTH_WHITELIST)
                                        .permitAll()
                                        .mvcMatchers(HttpMethod.GET, "/ads", "/image/**")
                                        .permitAll()
                                        .mvcMatchers("/ads/**", "/users/**")
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
