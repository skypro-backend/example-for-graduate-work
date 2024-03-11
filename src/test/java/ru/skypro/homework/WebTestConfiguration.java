package ru.skypro.homework;

import org.springframework.boot.test.context.TestConfiguration;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Primary;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.provisioning.InMemoryUserDetailsManager;
import ru.skypro.homework.config.CustomUserDetails;
import ru.skypro.homework.dto.Role;

import java.util.Arrays;

@TestConfiguration
public class WebTestConfiguration {
    @Bean
    @Primary
    public UserDetailsService userDetailsService() {
        CustomUserDetails basicUser = new CustomUserDetails("user@gmail.com",
                "user", Role.USER);

        CustomUserDetails adminUser = new CustomUserDetails("admin@gmail.com",
                "admin", Role.ADMIN);

        return new InMemoryUserDetailsManager(Arrays.asList(
                basicUser, adminUser
        ));
    }
}
