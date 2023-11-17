package ru.skypro.homework.dto;

import java.util.Objects;

public class CreateOrUpdateAdDto {
    private String title;
    private Integer price;
    private String description;

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

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        CreateOrUpdateAdDto that = (CreateOrUpdateAdDto) o;
        return Objects.equals(title, that.title)
                && Objects.equals(price, that.price)
                && Objects.equals(description, that.description);
    }

    @Override
    public int hashCode() {
        return Objects.hash(title, price, description);
    }
}
