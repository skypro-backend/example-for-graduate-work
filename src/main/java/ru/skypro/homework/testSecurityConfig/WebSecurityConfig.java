package ru.skypro.homework.testSecurityConfig;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.provisioning.JdbcUserDetailsManager;
import org.springframework.security.web.SecurityFilterChain;
import ru.skypro.homework.dto.Role;

import javax.sql.DataSource;

import static org.springframework.security.config.Customizer.withDefaults;
@Configuration
@EnableWebSecurity
public class WebSecurityConfig {

        private static final String[] AUTH_WHITELIST = {
                "/swagger-resources/**",
                "/swagger-ui.html",
                "/v3/api-docs",
                "/webjars/**",
                "/login",
                "/register"
        };

        @Bean
        public JdbcUserDetailsManager userDetailsService(PasswordEncoder passwordEncoder) {
            UserDetails user =
                    User.builder()
                            .username("user@gmail.com")
                            .password("password")
                            .passwordEncoder(passwordEncoder::encode)
                            .roles(Role.USER.name())
                            .build();
            return new JdbcUserDetailsManager((DataSource) user);
        }

            @Bean
            public JdbcUserDetailsManager jdbcUserDetailsManager(DataSource dataSource) {
                JdbcUserDetailsManager userDetailsManager = new JdbcUserDetailsManager(dataSource);
                return userDetailsManager;
            }

        @Bean
        public SecurityFilterChain filterChain(HttpSecurity http) throws Exception {
            http.csrf()
                    .disable()
                    .authorizeHttpRequests(
                            authorization ->
                                    authorization
                                            .mvcMatchers(AUTH_WHITELIST)
                                            .permitAll()
                                            .mvcMatchers("/ads/**", "/users/**")
                                            .authenticated())
                    .cors()
                    .and()
                    .httpBasic(withDefaults());
            return http.build();
        }

        @EnableWebSecurity
        public class SecurityConfig extends WebSecurityConfigurerAdapter {

            @Autowired
            private JdbcUserDetailsManager jdbcUserDetailsManager;

            @Override
            protected void configure(HttpSecurity http) throws Exception {
                http
                        .authorizeRequests()
                        .antMatchers("/admin/**").hasRole("ADMIN")
                        .antMatchers("/user/**").hasRole("USER")
                        .anyRequest().authenticated()
                        .and()
                        .formLogin()
                        .and()
                        .httpBasic();
            }
            @Override
            public void configure(AuthenticationManagerBuilder auth) throws Exception {
                auth.jdbcAuthentication()
                        .dataSource(jdbcUserDetailsManager.getDataSource())
                        .withDefaultSchema()
                        .withUser("user").password("password").roles("USER")
                        .and()
                        .withUser("admin").password("password").roles("USER", "ADMIN");
            }
        }


        @Bean
        public PasswordEncoder passwordEncoder() {
            return new BCryptPasswordEncoder();
        }
    @PreAuthorize("hasRole('ADMIN')")
    public void editComment(long commentId) {
        // Реализация метода
    }


    }

