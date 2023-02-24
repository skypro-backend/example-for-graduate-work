package ru.skypro.homework;

import static org.springframework.security.config.Customizer.withDefaults;

import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.authentication.dao.DaoAuthenticationProvider;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;

@Configuration
public class WebSecurityConfig {


  private static final String[] AUTH_WHITELIST = {
      "/swagger-resources/**",
      "/swagger-ui.html",
      "/v3/api-docs",
      "/webjars/**",
      "/login", "/register"
  };
  private final UserDetailsService userDetailService;

  public WebSecurityConfig(
      @Qualifier("UserDetailServiceImpl") UserDetailsService userDetailService) {
    this.userDetailService = userDetailService;
  }

  @Bean
  protected DaoAuthenticationProvider daoAuthenticationProvider() {
    DaoAuthenticationProvider daoAuthenticationProvider = new DaoAuthenticationProvider();
    daoAuthenticationProvider.setPasswordEncoder(new BCryptPasswordEncoder());
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
        {
          try {
            authz
                .mvcMatchers(AUTH_WHITELIST).permitAll()
                .mvcMatchers(HttpMethod.GET, "/ads").permitAll()
                .mvcMatchers("/ads/**", "/users/**")
                .authenticated();
          } catch (Exception e) {
            throw new RuntimeException(e);
          }
        })
        .cors().and()
        .httpBasic(withDefaults());
    return http.build();
  }


}

