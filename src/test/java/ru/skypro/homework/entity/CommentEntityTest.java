package ru.skypro.homework.entity;

import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;

import java.time.LocalDateTime;

class CommentEntityTest {

    @Test
    void shouldSetAndGetFieldsCorrectly() {
        // Given
        CommentEntity comment = new CommentEntity();

        AdEntity ad = new AdEntity();
        ad.setPk(1);

        UserEntity author = new UserEntity();
        author.setId(1);
        author.setEmail("author@test.com");

        // When
        comment.setPk(1);
        comment.setText("Отличное объявление!");
        comment.setCreatedAt(LocalDateTime.of(2023, 10, 1, 12, 0));
        comment.setAd(ad);
        comment.setAuthor(author);

        // Then
        assertThat(comment.getPk()).isEqualTo(1);
        assertThat(comment.getText()).isEqualTo("Отличное объявление!");
        assertThat(comment.getCreatedAt()).isEqualTo(LocalDateTime.of(2023, 10, 1, 12, 0));
        assertThat(comment.getAd().getPk()).isEqualTo(1);
        assertThat(comment.getAuthor().getId()).isEqualTo(1);
        assertThat(comment.getAuthor().getEmail()).isEqualTo("author@test.com");
    }

    @Test
    void shouldHandleNullValuesGracefully() {
        // Given
        CommentEntity comment = new CommentEntity();

        // When & Then
        comment.setPk(null);
        comment.setText(null);
        comment.setCreatedAt(null);
        comment.setAd(null);
        comment.setAuthor(null);

        assertThat(comment.getPk()).isNull();
        assertThat(comment.getText()).isNull();
        assertThat(comment.getCreatedAt()).isNull();
        assertThat(comment.getAd()).isNull();
        assertThat(comment.getAuthor()).isNull();
    }

    @Test
    void shouldBeEqualWhenSameContent() {
        // Given
        AdEntity ad = new AdEntity();
        ad.setPk(1);

        UserEntity author = new UserEntity();
        author.setId(1);

        CommentEntity comment1 = CommentEntity.builder().pk(1).text("Хорошо!").createdAt(LocalDateTime.now()).ad(ad).author(author).build();

        CommentEntity comment2 = CommentEntity.builder().pk(1).text("Хорошо!").createdAt(comment1.getCreatedAt()).ad(ad).author(author).build();

        // Then
        assertThat(comment1).usingRecursiveComparison().ignoringFields("createdAt").isEqualTo(comment2);
    }
}