package ru.skypro.homework.dto;

import javax.validation.constraints.*;

public class CreateOrUpdateAd {
    @NotBlank
    @Size(min = 4, max = 32)
    private String title;

    @NotNull
    @Min(0)
    @Max(10000000)
    private Integer price;

    @NotBlank
    @Size(min = 8, max = 64)
    private String description;

    public CreateOrUpdateAd() {
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public Integer getPrice() {
        return price;
    }

    public void setPrice(Integer price) {
        this.price = price;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }
}