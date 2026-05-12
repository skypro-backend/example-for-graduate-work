package ru.skypro.homework.entity;

import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;

import java.time.LocalDateTime;
import java.util.List;

class AdEntityTest {

    @Test
    void shouldSetAndGetFieldsCorrectly() {
        // Given
        UserEntity author = new UserEntity();
        author.setId(1);
        author.setEmail("author@test.com");

        AdEntity ad = new AdEntity();

        // When
        ad.setPk(1);
        ad.setPrice(10000);
        ad.setTitle("Велосипед");
        ad.setDescription("Как новый, без царапин");
        ad.setCreatedAt(LocalDateTime.of(2023, 10, 1, 12, 0));
        ad.setAuthor(author);

        // Then
        assertThat(ad.getPk()).isEqualTo(1);
        assertThat(ad.getPrice()).isEqualTo(10000);
        assertThat(ad.getTitle()).isEqualTo("Велосипед");
        assertThat(ad.getDescription()).isEqualTo("Как новый, без царапин");
        assertThat(ad.getCreatedAt()).isEqualTo(LocalDateTime.of(2023, 10, 1, 12, 0));
        assertThat(ad.getAuthor()).isNotNull();
        assertThat(ad.getAuthor().getId()).isEqualTo(1);
        assertThat(ad.getAuthor().getEmail()).isEqualTo("author@test.com");
    }

    @Test
    void shouldHandleNullValuesGracefully() {
        // Given
        AdEntity ad = new AdEntity();

        // When & Then
        ad.setPk(null);
        ad.setPrice(null);
        ad.setTitle(null);
        ad.setDescription(null);
        ad.setCreatedAt(null);
        ad.setAuthor(null);

        assertThat(ad.getPk()).isNull();
        assertThat(ad.getPrice()).isNull();
        assertThat(ad.getTitle()).isNull();
        assertThat(ad.getDescription()).isNull();
        assertThat(ad.getCreatedAt()).isNull();
        assertThat(ad.getAuthor()).isNull();
    }

    @Test
    void shouldWorkWithCommentsList() {
        // Given
        CommentEntity comment1 = new CommentEntity();
        comment1.setPk(1);
        comment1.setText("Отличное объявление!");

        CommentEntity comment2 = new CommentEntity();
        comment2.setPk(2);
        comment2.setText("Беру!");

        AdEntity ad = new AdEntity();
        ad.setComments(List.of(comment1, comment2));

        // Then
        assertThat(ad.getComments()).hasSize(2);
        assertThat(ad.getComments().get(0).getText()).isEqualTo("Отличное объявление!");
        assertThat(ad.getComments().get(1).getText()).isEqualTo("Беру!");
    }

    @Test
    void shouldBeEqualWhenSameId() {
        // Given
        AdEntity ad1 = AdEntity.builder().pk(1).title("Велосипед").price(10000).build();
        AdEntity ad2 = AdEntity.builder().pk(1).title("Велосипед").price(10000).build();

        // Using equals from @EqualsAndHashCode (if it were present), but here we rely on builder and field comparison
        assertThat(ad1.getPk()).isEqualTo(ad2.getPk());
        assertThat(ad1).usingRecursiveComparison().ignoringFields("comments", "author").isEqualTo(ad2);
    }
}