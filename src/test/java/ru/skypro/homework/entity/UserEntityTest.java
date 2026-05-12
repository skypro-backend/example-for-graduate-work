package ru.skypro.homework.entity;

import org.junit.jupiter.api.Test;
import org.springframework.security.core.GrantedAuthority;

import static org.assertj.core.api.Assertions.assertThat;

import java.util.Collection;

class UserEntityTest {

    @Test
    void shouldSetAndGetFieldsCorrectly() {
        // Given
        UserEntity user = new UserEntity();

        // When
        user.setId(1);
        user.setFirstName("Иван");
        user.setLastName("Иванов");
        user.setPhone("+79991234567");
        user.setEmail("user@gmail.com");
        user.setPassword("password123");
        user.setRole(Role.USER);

        // Then
        assertThat(user.getId()).isEqualTo(1);
        assertThat(user.getFirstName()).isEqualTo("Иван");
        assertThat(user.getLastName()).isEqualTo("Иванов");
        assertThat(user.getPhone()).isEqualTo("+79991234567");
        assertThat(user.getEmail()).isEqualTo("user@gmail.com");
        assertThat(user.getPassword()).isEqualTo("password123");
        assertThat(user.getRole()).isEqualTo(Role.USER);
    }

    @Test
    void shouldReturnAuthoritiesBasedOnRole() {
        // Given
        UserEntity user = new UserEntity();
        user.setRole(Role.ADMIN);

        // When
        Collection<? extends GrantedAuthority> authorities = user.getAuthorities();

        // Then
        assertThat(authorities).hasSize(1);
        assertThat(authorities.iterator().next().getAuthority()).isEqualTo("ROLE_ADMIN");
    }

    @Test
    void shouldUseEmailAsUsername() {
        // Given
        UserEntity user = new UserEntity();
        user.setEmail("user@gmail.com");

        // When
        String username = user.getUsername();

        // Then
        assertThat(username).isEqualTo("user@gmail.com");
    }

    @Test
    void shouldReturnTrueForUserDetailsStatusMethods() {
        // Given
        UserEntity user = new UserEntity();

        // Then
        assertThat(user.isAccountNonExpired()).isTrue();
        assertThat(user.isAccountNonLocked()).isTrue();
        assertThat(user.isCredentialsNonExpired()).isTrue();
        assertThat(user.isEnabled()).isTrue();
    }
}