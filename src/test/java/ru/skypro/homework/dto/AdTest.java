package ru.skypro.homework.dto;

import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;

class AdTest {

    @Test
    void shouldSetAndGetFieldsCorrectly() {
        // Given
        Ad ad = new Ad();

        // When
        ad.setAuthor(1);
        ad.setImage("/image/ad/1");
        ad.setPk(1);
        ad.setPrice(10000);
        ad.setTitle("Велосипед");

        // Then
        assertThat(ad.getAuthor()).isEqualTo(1);
        assertThat(ad.getImage()).isEqualTo("/image/ad/1");
        assertThat(ad.getPk()).isEqualTo(1);
        assertThat(ad.getPrice()).isEqualTo(10000);
        assertThat(ad.getTitle()).isEqualTo("Велосипед");
    }

    @Test
    void shouldConstructAdWithNonNullFields() {
        // Given
        Ad ad = new Ad();
        ad.setPk(5);
        ad.setTitle("Квартира");
        ad.setPrice(5000000);

        // Then
        assertThat(ad.getPk()).isNotNull();
        assertThat(ad.getTitle()).isNotBlank();
        assertThat(ad.getPrice()).isPositive();
    }

    @Test
    void shouldBeEqualWhenSameContent() {
        // Given
        Ad ad1 = new Ad();
        ad1.setPk(1);
        ad1.setTitle("Велосипед");
        ad1.setPrice(10000);

        Ad ad2 = new Ad();
        ad2.setPk(1);
        ad2.setTitle("Велосипед");
        ad2.setPrice(10000);

        // Then
        assertThat(ad1).usingRecursiveComparison().isEqualTo(ad2);
    }
}