package ru.skypro.homework.dto;

import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;

class LoginTest {

    @Test
    void shouldSetAndGetFieldsCorrectly() {
        // Given
        Login login = new Login();

        // When
        login.setUsername("user@gmail.com");
        login.setPassword("password123");

        // Then
        assertThat(login.getUsername()).isEqualTo("user@gmail.com");
        assertThat(login.getPassword()).isEqualTo("password123");
    }

    @Test
    void shouldHandleNullValuesGracefully() {
        // Given
        Login login = new Login();

        // When
        login.setUsername(null);
        login.setPassword(null);

        // Then
        assertThat(login.getUsername()).isNull();
        assertThat(login.getPassword()).isNull();
    }

    @Test
    void shouldBeEqualWhenSameContent() {
        // Given
        Login login1 = new Login();
        login1.setUsername("user@gmail.com");
        login1.setPassword("password123");

        Login login2 = new Login();
        login2.setUsername("user@gmail.com");
        login2.setPassword("password123");

        // Then
        assertThat(login1).usingRecursiveComparison().isEqualTo(login2);
    }
}