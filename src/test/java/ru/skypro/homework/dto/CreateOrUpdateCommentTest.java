package ru.skypro.homework.dto;

import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;

class CreateOrUpdateCommentTest {

    @Test
    void shouldSetAndGetTextCorrectly() {
        // Given
        CreateOrUpdateComment dto = new CreateOrUpdateComment();

        // When
        dto.setText("Хорошее объявление!");

        // Then
        assertThat(dto.getText()).isEqualTo("Хорошее объявление!");
    }

    @Test
    void shouldHandleNullTextGracefully() {
        // Given
        CreateOrUpdateComment dto = new CreateOrUpdateComment();

        // When
        dto.setText(null);

        // Then
        assertThat(dto.getText()).isNull();
    }

    @Test
    void shouldBeEqualWhenSameContent() {
        // Given
        CreateOrUpdateComment dto1 = new CreateOrUpdateComment();
        dto1.setText("Хорошее объявление!");

        CreateOrUpdateComment dto2 = new CreateOrUpdateComment();
        dto2.setText("Хорошее объявление!");

        // Then
        assertThat(dto1).usingRecursiveComparison().isEqualTo(dto2);
    }
}