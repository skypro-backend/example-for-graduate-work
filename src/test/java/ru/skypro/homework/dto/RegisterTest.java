package ru.skypro.homework.dto;

import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;

import ru.skypro.homework.entity.Role;

class RegisterTest {

    @Test
    void shouldSetAndGetFieldsCorrectly() {
        // Given
        Register register = new Register();

        // When
        register.setUsername("user@gmail.com");
        register.setPassword("password123");
        register.setFirstName("Иван");
        register.setLastName("Иванов");
        register.setPhone("+7 (999) 123-45-67");
        register.setRole(Role.USER);

        // Then
        assertThat(register.getUsername()).isEqualTo("user@gmail.com");
        assertThat(register.getPassword()).isEqualTo("password123");
        assertThat(register.getFirstName()).isEqualTo("Иван");
        assertThat(register.getLastName()).isEqualTo("Иванов");
        assertThat(register.getPhone()).isEqualTo("+7 (999) 123-45-67");
        assertThat(register.getRole()).isEqualTo(Role.USER);
    }

    @Test
    void shouldHandleNullValuesGracefully() {
        // Given
        Register register = new Register();

        // When
        register.setUsername(null);
        register.setPassword(null);
        register.setFirstName(null);
        register.setLastName(null);
        register.setPhone(null);
        register.setRole(null);

        // Then
        assertThat(register.getUsername()).isNull();
        assertThat(register.getPassword()).isNull();
        assertThat(register.getFirstName()).isNull();
        assertThat(register.getLastName()).isNull();
        assertThat(register.getPhone()).isNull();
        assertThat(register.getRole()).isNull();
    }

    @Test
    void shouldBeEqualWhenSameContent() {
        // Given
        Register register1 = new Register();
        register1.setUsername("user@gmail.com");
        register1.setPassword("password123");
        register1.setFirstName("Иван");
        register1.setLastName("Иванов");
        register1.setPhone("+7 (999) 123-45-67");
        register1.setRole(Role.ADMIN);

        Register register2 = new Register();
        register2.setUsername("user@gmail.com");
        register2.setPassword("password123");
        register2.setFirstName("Иван");
        register2.setLastName("Иванов");
        register2.setPhone("+7 (999) 123-45-67");
        register2.setRole(Role.ADMIN);

        // Then
        assertThat(register1).usingRecursiveComparison().isEqualTo(register2);
    }
}