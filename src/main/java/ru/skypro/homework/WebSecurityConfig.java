package ru.skypro.homework;

import static org.springframework.security.config.Customizer.withDefaults;

import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.dao.DaoAuthenticationProvider;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;
import ru.skypro.homework.security.UserDetailServiceImpl;

@Configuration
public class WebSecurityConfig {


  private static final String[] AUTH_WHITELIST = {
      "/swagger-resources/**",
      "/swagger-ui.html",
      "/v3/api-docs",
      "/webjars/**",
      "/login", "/register"
  };
  private final UserDetailServiceImpl userDetailService;

  public WebSecurityConfig(
      @Qualifier("UserDetailServiceImpl") UserDetailServiceImpl userDetailService) {
    this.userDetailService = userDetailService;
  }

  @Bean
  protected DaoAuthenticationProvider daoAuthenticationProvider() {
    DaoAuthenticationProvider daoAuthenticationProvider = new DaoAuthenticationProvider();
    daoAuthenticationProvider.setPasswordEncoder(passwordEncoder());
    daoAuthenticationProvider.setUserDetailsService(userDetailService);
    return daoAuthenticationProvider;
  }

  @Bean
  protected AuthenticationManagerBuilder authenticationManagerBuilder(
      AuthenticationManagerBuilder auth
  ) {
    return auth.authenticationProvider(daoAuthenticationProvider());
  }


  @Bean
  public SecurityFilterChain filterChain(HttpSecurity http) throws Exception {
    http
        .csrf().disable()
        .authorizeHttpRequests((authz) ->
            authz
                .mvcMatchers(AUTH_WHITELIST).permitAll()
                .mvcMatchers("/ads/**", "/users/**").authenticated()

        )
        .cors().disable()
        .httpBasic(withDefaults());
    return http.build();
  }


  @Bean
  public PasswordEncoder passwordEncoder() {
    return new BCryptPasswordEncoder();
  }

}

