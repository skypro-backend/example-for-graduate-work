package ru.skypro.homework.dto;

import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;

class UpdateUserTest {

    @Test
    void shouldSetAndGetFieldsCorrectly() {
        // Given
        UpdateUser updateUser = new UpdateUser();

        // When
        updateUser.setFirstName("Иван");
        updateUser.setLastName("Иванов");
        updateUser.setPhone("+7 (999) 123-45-67");

        // Then
        assertThat(updateUser.getFirstName()).isEqualTo("Иван");
        assertThat(updateUser.getLastName()).isEqualTo("Иванов");
        assertThat(updateUser.getPhone()).isEqualTo("+7 (999) 123-45-67");
    }

    @Test
    void shouldHandleNullValuesGracefully() {
        // Given
        UpdateUser dto = new UpdateUser();

        // When
        dto.setFirstName(null);
        dto.setLastName(null);
        dto.setPhone(null);

        // Then
        assertThat(dto.getFirstName()).isNull();
        assertThat(dto.getLastName()).isNull();
        assertThat(dto.getPhone()).isNull();
    }

    @Test
    void shouldBeEqualWhenSameContent() {
        // Given
        UpdateUser dto1 = new UpdateUser();
        dto1.setFirstName("Иван");
        dto1.setLastName("Иванов");
        dto1.setPhone("+7 (999) 123-45-67");

        UpdateUser dto2 = new UpdateUser();
        dto2.setFirstName("Иван");
        dto2.setLastName("Иванов");
        dto2.setPhone("+7 (999) 123-45-67");

        // Then
        assertThat(dto1).usingRecursiveComparison().isEqualTo(dto2);
    }
}