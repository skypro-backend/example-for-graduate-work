package ru.skypro.homework.dto;

import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;

class CommentTest {

    @Test
    void shouldSetAndGetFieldsCorrectly() {
        // Given
        Comment comment = new Comment();

        // When
        comment.setAuthor(1);
        comment.setAuthorImage("/images/user/1.jpg");
        comment.setAuthorFirstName("Иван");
        comment.setCreatedAt(1700000000000L);
        comment.setPk(1);
        comment.setText("Отличное объявление!");

        // Then
        assertThat(comment.getAuthor()).isEqualTo(1);
        assertThat(comment.getAuthorImage()).isEqualTo("/images/user/1.jpg");
        assertThat(comment.getAuthorFirstName()).isEqualTo("Иван");
        assertThat(comment.getCreatedAt()).isEqualTo(1700000000000L);
        assertThat(comment.getPk()).isEqualTo(1);
        assertThat(comment.getText()).isEqualTo("Отличное объявление!");
    }

    @Test
    void shouldHandleNullTextAndEmptyValues() {
        // Given
        Comment comment = new Comment();

        // When
        comment.setAuthor(null);
        comment.setAuthorImage(null);
        comment.setAuthorFirstName("");
        comment.setCreatedAt(null);
        comment.setPk(null);
        comment.setText(null);

        // Then
        assertThat(comment.getAuthor()).isNull();
        assertThat(comment.getAuthorImage()).isNull();
        assertThat(comment.getAuthorFirstName()).isBlank();
        assertThat(comment.getCreatedAt()).isNull();
        assertThat(comment.getPk()).isNull();
        assertThat(comment.getText()).isNull();
    }

    @Test
    void shouldBeEqualWhenSameContent() {
        // Given
        Comment comment1 = new Comment();
        comment1.setAuthor(1);
        comment1.setAuthorImage("/images/user/1.jpg");
        comment1.setAuthorFirstName("Иван");
        comment1.setCreatedAt(1700000000000L);
        comment1.setPk(1);
        comment1.setText("Отличное объявление!");

        Comment comment2 = new Comment();
        comment2.setAuthor(1);
        comment2.setAuthorImage("/images/user/1.jpg");
        comment2.setAuthorFirstName("Иван");
        comment2.setCreatedAt(1700000000000L);
        comment2.setPk(1);
        comment2.setText("Отличное объявление!");

        // Then
        assertThat(comment1).usingRecursiveComparison().isEqualTo(comment2);
    }
}