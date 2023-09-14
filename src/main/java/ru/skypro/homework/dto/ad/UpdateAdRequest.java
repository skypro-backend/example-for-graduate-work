package ru.skypro.homework.dto.ad;

import lombok.NoArgsConstructor;
import lombok.RequiredArgsConstructor;

import java.util.Objects;


public class UpdateAdRequest {
    private String title;
    private Integer price;
    private String description;

    public UpdateAdRequest(String title, Integer price, String description) {
        this.title = title;
        this.price = price;
        this.description = description;
    }

    public UpdateAdRequest() {
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

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        UpdateAdRequest that = (UpdateAdRequest) o;
        return Objects.equals(title, that.title) && Objects.equals(price, that.price) && Objects.equals(description, that.description);
    }

    @Override
    public int hashCode() {
        return Objects.hash(title, price, description);
    }

    @Override
    public String toString() {
        return "UpdateAdRequest{" +
                "title='" + title + '\'' +
                ", price=" + price +
                ", description='" + description + '\'' +
                '}';
    }
}