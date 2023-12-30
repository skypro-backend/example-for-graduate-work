package ru.skypro.homework.filter;

import org.springframework.boot.autoconfigure.AutoConfiguration;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
/*
@Configuration
@EnableWebSecurity
public class SecurityConfig extends WebSecurityConfigurerAdapter {

    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http
                .authorizeRequests()
                .antMatchers("/public/**").permitAll() // разрешаем доступ к открытым страницам
                .anyRequest().authenticated() // все остальные страницы требуют авторизации
                .and()
                .formLogin() // настраиваем форму входа
                .loginPage("/login") // указываем URL страницы логина
                .permitAll() // разрешаем доступ к странице логина
                .and()
                .logout() // настраиваем выход из системы
                .permitAll(); // разрешаем доступ к выходу из системы
    }

    @Override
    protected void configure(AuthenticationManagerBuilder auth) throws Exception {
        auth
                .inMemoryAuthentication()
                .withUser("user").password(passwordEncoder().encode("password")).roles("USER") // создаем пользователя "user" с паролем "password" и ролью "USER"
                .and()
                .withUser("admin").password(passwordEncoder().encode("admin")).roles("ADMIN"); // создаем пользователя "admin" с паролем "admin" и ролью "ADMIN"
    }

   @Bean
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();  }

}
*/