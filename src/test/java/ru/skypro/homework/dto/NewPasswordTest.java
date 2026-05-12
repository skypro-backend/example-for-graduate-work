package ru.skypro.homework.dto;

import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;

class NewPasswordTest {

    @Test
    void shouldSetAndGetFieldsCorrectly() {
        // Given
        NewPassword newPassword = new NewPassword();

        // When
        newPassword.setCurrentPassword("oldpass123");
        newPassword.setNewPassword("newpass123");

        // Then
        assertThat(newPassword.getCurrentPassword()).isEqualTo("oldpass123");
        assertThat(newPassword.getNewPassword()).isEqualTo("newpass123");
    }

    @Test
    void shouldHandleNullValuesGracefully() {
        // Given
        NewPassword dto = new NewPassword();

        // When
        dto.setCurrentPassword(null);
        dto.setNewPassword(null);

        // Then
        assertThat(dto.getCurrentPassword()).isNull();
        assertThat(dto.getNewPassword()).isNull();
    }

    @Test
    void shouldBeEqualWhenSameContent() {
        // Given
        NewPassword dto1 = new NewPassword();
        dto1.setCurrentPassword("oldpass123");
        dto1.setNewPassword("newpass123");

        NewPassword dto2 = new NewPassword();
        dto2.setCurrentPassword("oldpass123");
        dto2.setNewPassword("newpass123");

        // Then
        assertThat(dto1).usingRecursiveComparison().isEqualTo(dto2);
    }
}