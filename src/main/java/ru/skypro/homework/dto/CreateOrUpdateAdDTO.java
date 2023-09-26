package ru.skypro.homework.dto;

import javax.validation.constraints.*;

public class CreateOrUpdateAdDTO {

    @Min(0)
    @Max(10_000_000)
    private int price;

    @Size(min = 4, max = 32)
    private String title;

    @Size(min = 8, max = 64)
    private String description;

    public int getPrice() {
        return price;
    }

    public void setPrice(int price) {
        this.price = price;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }
}
