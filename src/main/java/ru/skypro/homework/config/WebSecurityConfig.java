package ru.skypro.homework.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.provisioning.InMemoryUserDetailsManager;
import org.springframework.security.web.SecurityFilterChain;
import ru.skypro.homework.dto.Role;

import static org.springframework.security.config.Customizer.withDefaults;


//@Override
//protected void configure(HttpSecurity http) throws Exception {
//    http
//            .authorizeRequests()
//            .antMatchers("/public").permitAll() // указываем эндпоинт, который должен быть доступен всем
//            .anyRequest().authenticated() // все остальные эндпоинты требуют аутентификации
//            .and()
//            .formLogin()
//            .and()
//            .httpBasic();
//}
//}
//В этом примере эндпоинт `/public` доступен для всех пользователей без аутентификации, а все остальные эндпоинты требуют аутентификации.
// Обратите внимание, что этот пример использует базовую аутентификацию (Basic Authentication) и форму аутентификации (Form Login). Вы можете настроить другие методы аутентификации, такие как OAuth, в зависимости от ваших требований.
//Надеюсь, это поможет вам настроить Spring Security для неавторизованных пользователей!

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

    @Bean
    public InMemoryUserDetailsManager userDetailsService(PasswordEncoder passwordEncoder) {
        UserDetails user =
                User.builder()
                        .username("user@gmail.com")
                        .password("password")
                        .passwordEncoder(passwordEncoder::encode)
                        .roles(Role.USER.name())
                        .build();
        return new InMemoryUserDetailsManager(user);
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

    @Bean
    public SecurityFilterChain filterNoChain(HttpSecurity httpSecurity) throws Exception {
        httpSecurity.authorizeRequests().antMatchers("/ads/**").permitAll().and().formLogin().and().httpBasic();
        return httpSecurity.build();
    }

    

    @Bean
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }
}
