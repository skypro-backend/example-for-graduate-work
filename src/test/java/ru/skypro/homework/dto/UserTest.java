package ru.skypro.homework.dto;

import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;

import ru.skypro.homework.entity.Role;

class UserTest {

    @Test
    void shouldSetAndGetFieldsCorrectly() {
        // Given
        User user = new User();

        // When
        user.setId(1);
        user.setEmail("user@gmail.com");
        user.setFirstName("Иван");
        user.setLastName("Иванов");
        user.setPhone("+79991234567");
        user.setRole(Role.USER);
        user.setImage("/images/user/1.jpg");

        // Then
        assertThat(user.getId()).isEqualTo(1);
        assertThat(user.getEmail()).isEqualTo("user@gmail.com");
        assertThat(user.getFirstName()).isEqualTo("Иван");
        assertThat(user.getLastName()).isEqualTo("Иванов");
        assertThat(user.getPhone()).isEqualTo("+79991234567");
        assertThat(user.getRole()).isEqualTo(Role.USER);
        assertThat(user.getImage()).isEqualTo("/images/user/1.jpg");
    }

    @Test
    void shouldHandleNullValuesGracefully() {
        // Given
        User user = new User();

        // When
        user.setId(null);
        user.setEmail(null);
        user.setFirstName(null);
        user.setLastName(null);
        user.setPhone(null);
        user.setRole(null);
        user.setImage(null);

        // Then
        assertThat(user.getId()).isNull();
        assertThat(user.getEmail()).isNull();
        assertThat(user.getFirstName()).isNull();
        assertThat(user.getLastName()).isNull();
        assertThat(user.getPhone()).isNull();
        assertThat(user.getRole()).isNull();
        assertThat(user.getImage()).isNull();
    }

    @Test
    void shouldBeEqualWhenSameContent() {
        // Given
        User user1 = new User();
        user1.setId(1);
        user1.setEmail("user@gmail.com");
        user1.setFirstName("Иван");
        user1.setLastName("Иванов");
        user1.setPhone("+79991234567");
        user1.setRole(Role.ADMIN);
        user1.setImage("/images/user/1.jpg");

        User user2 = new User();
        user2.setId(1);
        user2.setEmail("user@gmail.com");
        user2.setFirstName("Иван");
        user2.setLastName("Иванов");
        user2.setPhone("+79991234567");
        user2.setRole(Role.ADMIN);
        user2.setImage("/images/user/1.jpg");

        // Then
        assertThat(user1).usingRecursiveComparison().isEqualTo(user2);
    }
}