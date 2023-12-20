package ru.skypro.homework.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.jdbc.DataSourceProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.jdbc.datasource.DriverManagerDataSource;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.provisioning.InMemoryUserDetailsManager;
import org.springframework.security.provisioning.JdbcUserDetailsManager;
import org.springframework.security.web.SecurityFilterChain;
import ru.skypro.homework.dto.Role;

import javax.sql.DataSource;

import static org.springframework.security.config.Customizer.withDefaults;

@Configuration
public class WebSecurityConfig {

    private static final String[] AUTH_WHITELIST = {
            "/swagger-resources/**",
            "/swagger-ui.html",
            "/v3/api-docs",
            "/webjars/**",
            "/login",
            "/register"
    };

    @Autowired
    private DataSourceProperties dataSourceProperties;

    @Bean
    public DataSource dataSource(){
        DriverManagerDataSource dataSource = new DriverManagerDataSource();
        dataSource.setDriverClassName(dataSourceProperties.getDriverClassName());
        dataSource.setUrl(dataSourceProperties.getUrl());
        dataSource.setUsername(dataSourceProperties.getUsername());
        dataSource.setPassword(dataSourceProperties.getPassword());
        return dataSource;
    }

    @Bean
    public JdbcUserDetailsManager userDetailsService(PasswordEncoder passwordEncoder) {
        JdbcUserDetailsManager userDetailsManager = new JdbcUserDetailsManager(dataSource());
        userDetailsManager.setUsersByUsernameQuery(
                "SELECT login, password, enabled FROM users WHERE login = ?");
        userDetailsManager.setAuthoritiesByUsernameQuery(
                "SELECT login, role FROM users WHERE login = ?");
        userDetailsManager.setCreateUserSql(
                "INSERT INTO users (login, password, enabled) VALUES (?, ?, ?)");
        userDetailsManager.setCreateAuthoritySql(
                "INSERT INTO users (login, role) VALUES (?, ?)");
        userDetailsManager.setDeleteUserSql(
                "DELETE FROM users WHERE login = ?");
        userDetailsManager.setDeleteUserAuthoritiesSql(
                "DELETE FROM users WHERE login = ?");
        return userDetailsManager;
    }

    @Bean
    public SecurityFilterChain filterChain(HttpSecurity http) throws Exception {
        http.csrf()
                .disable()
                .authorizeHttpRequests(
                        authorization ->
                                authorization
                                        .mvcMatchers(AUTH_WHITELIST).permitAll()
                                        .mvcMatchers("/ads/**").hasAnyRole(Role.USER.name(),Role.ADMIN.name())
                                        .mvcMatchers("users/set_password").authenticated()
                                        .mvcMatchers("/users/**").hasRole(Role.USER.name()))
                .cors()
                .and()
                .httpBasic(withDefaults());
        http.formLogin().loginPage("/login").permitAll();
        return http.build();
    }

    @Bean
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }

}
