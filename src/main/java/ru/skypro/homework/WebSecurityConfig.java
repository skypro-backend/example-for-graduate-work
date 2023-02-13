package ru.skypro.homework;

import org.postgresql.ds.PGSimpleDataSource;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.provisioning.JdbcUserDetailsManager;
import org.springframework.security.web.SecurityFilterChain;

@Configuration
public class WebSecurityConfig {
    @Value("${spring.datasource.url}")
    private String databaseUrl;

    @Value("${spring.datasource.username}")
    private String databaseUser;

    @Value("${spring.datasource.password}")
    private String databaseUserPassword;
    private static final String[] AUTH_WHITELIST = {
            "/swagger-resources/**",
            "/swagger-ui.html",
            "/v3/api-docs",
            "/webjars/**",
            "/login", "/register"
    };

    @Bean
    public JdbcUserDetailsManager userDetailsService() {
        PGSimpleDataSource dbSource = new PGSimpleDataSource();
      dbSource.setServerNames(null);
        dbSource.setDatabaseName(databaseUrl.substring(databaseUrl.lastIndexOf("/") + 1));
        dbSource.setUser(databaseUser);
        dbSource.setPassword(databaseUserPassword);
        return new JdbcUserDetailsManager(dbSource);
    }

    @Bean
    public SecurityFilterChain filterChain(HttpSecurity http) throws Exception {
        http.cors().and().csrf().disable().authorizeRequests()
                .antMatchers(HttpMethod.POST, "/login", "/register").permitAll()
                .antMatchers(HttpMethod.GET, "/ads").permitAll()
                .antMatchers(HttpMethod.POST, "/ads").authenticated()
                .antMatchers("/ads/**", "/users/**").authenticated()
                .mvcMatchers(AUTH_WHITELIST).permitAll()
                .and()
                .httpBasic();

        return http.build();

    }


}

