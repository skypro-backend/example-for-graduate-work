package ru.skypro.homework.dto;

import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;

class ExtendedAdTest {

    @Test
    void shouldSetAndGetFieldsCorrectly() {
        // Given
        ExtendedAd extendedAd = new ExtendedAd();

        // When
        extendedAd.setPk(1);
        extendedAd.setAuthorFirstName("Иван");
        extendedAd.setAuthorLastName("Иванов");
        extendedAd.setDescription("Как новый, без царапин");
        extendedAd.setEmail("user@gmail.com");
        extendedAd.setImage("/image/ad/1");
        extendedAd.setPhone("+79991234567");
        extendedAd.setPrice(10000);
        extendedAd.setTitle("Велосипед");
        extendedAd.setAuthorImage("/image/user/1");

        // Then
        assertThat(extendedAd.getPk()).isEqualTo(1);
        assertThat(extendedAd.getAuthorFirstName()).isEqualTo("Иван");
        assertThat(extendedAd.getAuthorLastName()).isEqualTo("Иванов");
        assertThat(extendedAd.getDescription()).isEqualTo("Как новый, без царапин");
        assertThat(extendedAd.getEmail()).isEqualTo("user@gmail.com");
        assertThat(extendedAd.getImage()).isEqualTo("/image/ad/1");
        assertThat(extendedAd.getPhone()).isEqualTo("+79991234567");
        assertThat(extendedAd.getPrice()).isEqualTo(10000);
        assertThat(extendedAd.getTitle()).isEqualTo("Велосипед");
        assertThat(extendedAd.getAuthorImage()).isEqualTo("/image/user/1");
    }

    @Test
    void shouldHandleNullValuesGracefully() {
        // Given
        ExtendedAd extendedAd = new ExtendedAd();

        // When
        extendedAd.setPk(null);
        extendedAd.setAuthorFirstName(null);
        extendedAd.setAuthorLastName(null);
        extendedAd.setDescription(null);
        extendedAd.setEmail(null);
        extendedAd.setImage(null);
        extendedAd.setPhone(null);
        extendedAd.setPrice(null);
        extendedAd.setTitle(null);
        extendedAd.setAuthorImage(null);

        // Then
        assertThat(extendedAd.getPk()).isNull();
        assertThat(extendedAd.getAuthorFirstName()).isNull();
        assertThat(extendedAd.getAuthorLastName()).isNull();
        assertThat(extendedAd.getDescription()).isNull();
        assertThat(extendedAd.getEmail()).isNull();
        assertThat(extendedAd.getImage()).isNull();
        assertThat(extendedAd.getPhone()).isNull();
        assertThat(extendedAd.getPrice()).isNull();
        assertThat(extendedAd.getTitle()).isNull();
        assertThat(extendedAd.getAuthorImage()).isNull();
    }

    @Test
    void shouldBeEqualWhenSameContent() {
        // Given
        ExtendedAd ad1 = new ExtendedAd();
        ad1.setPk(1);
        ad1.setAuthorFirstName("Иван");
        ad1.setAuthorLastName("Иванов");
        ad1.setTitle("Велосипед");
        ad1.setPrice(10000);
        ad1.setDescription("Как новый");
        ad1.setEmail("user@gmail.com");
        ad1.setPhone("+79991234567");
        ad1.setImage("/image/ad/1");
        ad1.setAuthorImage("/image/user/1");

        ExtendedAd ad2 = new ExtendedAd();
        ad2.setPk(1);
        ad2.setAuthorFirstName("Иван");
        ad2.setAuthorLastName("Иванов");
        ad2.setTitle("Велосипед");
        ad2.setPrice(10000);
        ad2.setDescription("Как новый");
        ad2.setEmail("user@gmail.com");
        ad2.setPhone("+79991234567");
        ad2.setImage("/image/ad/1");
        ad2.setAuthorImage("/image/user/1");

        // Then
        assertThat(ad1).usingRecursiveComparison().isEqualTo(ad2);
    }
}