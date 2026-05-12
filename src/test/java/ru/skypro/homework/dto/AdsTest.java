package ru.skypro.homework.dto;

import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;

import java.util.List;

class AdsTest {

    @Test
    void shouldSetAndGetFieldsCorrectly() {
        // Given
        Ads ads = new Ads();
        List<Ad> adList = List.of(createAd(1, "Велосипед", 10000), createAd(2, "Квартира", 5000000));

        // When
        ads.setCount(2);
        ads.setResults(adList);

        // Then
        assertThat(ads.getCount()).isEqualTo(2);
        assertThat(ads.getResults()).hasSize(2);
        assertThat(ads.getResults().get(0).getTitle()).isEqualTo("Велосипед");
        assertThat(ads.getResults().get(1).getPrice()).isEqualTo(5000000);
    }

    @Test
    void shouldHandleEmptyResultsList() {
        // Given
        Ads ads = new Ads();

        // When
        ads.setCount(0);
        ads.setResults(List.of());

        // Then
        assertThat(ads.getCount()).isZero();
        assertThat(ads.getResults()).isEmpty();
    }

    @Test
    void shouldBeEqualWhenSameContent() {
        // Given
        Ads ads1 = new Ads();
        ads1.setCount(1);
        ads1.setResults(List.of(createAd(1, "Телефон", 10000)));

        Ads ads2 = new Ads();
        ads2.setCount(1);
        ads2.setResults(List.of(createAd(1, "Телефон", 10000)));

        // Then
        assertThat(ads1).usingRecursiveComparison().isEqualTo(ads2);
    }

    private Ad createAd(int pk, String title, int price) {
        Ad ad = new Ad();
        ad.setPk(pk);
        ad.setTitle(title);
        ad.setPrice(price);
        return ad;
    }
}