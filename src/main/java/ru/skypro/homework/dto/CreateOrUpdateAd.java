package ru.skypro.homework.dto;

import io.swagger.v3.oas.annotations.media.Schema;

import jakarta.validation.constraints.Max;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.Size;

@Schema(description = "Данные для создания или обновления объявления")
public class CreateOrUpdateAd {

    @Schema(description = "заголовок объявления",
            minLength = 4,
            maxLength = 32,
            example = "Продам iPhone 13")
    @Size(min = 4, max = 32)
    private String title;

    @Schema(description = "цена объявления",
            minimum = "0",
            maximum = "10000000",
            example = "50000")
    @Min(0)
    @Max(10000000)
    private int price;

    @Schema(description = "описание объявления",
            minLength = 8,
            maxLength = 64,
            example = "Отличный телефон, в хорошем состоянии")
    @Size(min = 8, max = 64)
    private String description;

    public CreateOrUpdateAd(String title, int price, String description) {
        this.title = title;
        this.price = price;
        this.description = description;
    }

    public String getTitle() {
        return title;
    }

    public int getPrice() {
        return price;
    }

    public String getDescription() {
        return description;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public void setPrice(int price) {
        this.price = price;
    }

    public void setDescription(String description) {
        this.description = description;
    }
}
