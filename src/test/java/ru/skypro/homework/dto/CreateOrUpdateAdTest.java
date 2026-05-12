package ru.skypro.homework.dto;

import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;

class CreateOrUpdateAdTest {

    @Test
    void shouldSetAndGetFieldsCorrectly() {
        // Given
        CreateOrUpdateAd dto = new CreateOrUpdateAd();

        // When
        dto.setTitle("Велосипед");
        dto.setPrice(10000);
        dto.setDescription("Как новый");

        // Then
        assertThat(dto.getTitle()).isEqualTo("Велосипед");
        assertThat(dto.getPrice()).isEqualTo(10000);
        assertThat(dto.getDescription()).isEqualTo("Как новый");
    }

    @Test
    void shouldHandleNullAndEmptyValuesGracefully() {
        // Given
        CreateOrUpdateAd dto = new CreateOrUpdateAd();

        // When
        dto.setTitle(null);
        dto.setDescription(null);
        dto.setPrice(null);

        // Then
        assertThat(dto.getTitle()).isNull();
        assertThat(dto.getDescription()).isNull();
        assertThat(dto.getPrice()).isNull();
    }

    @Test
    void shouldBeEqualWhenSameContent() {
        // Given
        CreateOrUpdateAd dto1 = new CreateOrUpdateAd();
        dto1.setTitle("Велосипед");
        dto1.setPrice(10000);
        dto1.setDescription("Как новый");

        CreateOrUpdateAd dto2 = new CreateOrUpdateAd();
        dto2.setTitle("Велосипед");
        dto2.setPrice(10000);
        dto2.setDescription("Как новый");

        // Then
        assertThat(dto1).usingRecursiveComparison().isEqualTo(dto2);
    }
}