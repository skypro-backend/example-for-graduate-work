package ru.skypro.homework.dto;

import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;

import java.util.List;

class CommentsTest {

    @Test
    void shouldSetAndGetFieldsCorrectly() {
        // Given
        Comments comments = new Comments();
        List<Comment> commentList = List.of(createComment(1, "Иван", "Отлично!"), createComment(2, "Мария", "Спасибо за объявление!"));

        // When
        comments.setCount(2);
        comments.setResults(commentList);

        // Then
        assertThat(comments.getCount()).isEqualTo(2);
        assertThat(comments.getResults()).hasSize(2);
        assertThat(comments.getResults().get(0).getAuthorFirstName()).isEqualTo("Иван");
        assertThat(comments.getResults().get(1).getText()).isEqualTo("Спасибо за объявление!");
    }

    @Test
    void shouldHandleEmptyResultsList() {
        // Given
        Comments comments = new Comments();

        // When
        comments.setCount(0);
        comments.setResults(List.of());

        // Then
        assertThat(comments.getCount()).isZero();
        assertThat(comments.getResults()).isEmpty();
    }

    @Test
    void shouldBeEqualWhenSameContent() {
        // Given
        Comments comments1 = new Comments();
        comments1.setCount(1);
        comments1.setResults(List.of(createComment(1, "Иван", "Хорошо!")));

        Comments comments2 = new Comments();
        comments2.setCount(1);
        comments2.setResults(List.of(createComment(1, "Иван", "Хорошо!")));

        // Then
        assertThat(comments1).usingRecursiveComparison().isEqualTo(comments2);
    }

    private Comment createComment(int pk, String firstName, String text) {
        Comment comment = new Comment();
        comment.setPk(pk);
        comment.setAuthorFirstName(firstName);
        comment.setText(text);
        return comment;
    }
}