package ru.skypro.homework.dto.ad;

import lombok.*;

import java.util.Objects;

public class AdDTO {
    private Long id;
    private String title;
    private Integer price;
    private String description;
    private Long author;
    private String image;

    public AdDTO(Long id, String title, Integer price, String description, Long author, String image) {
        this.id = id;
        this.title = title;
        this.price = price;
        this.description = description;
        this.author = author;
        this.image = image;
    }

    public AdDTO() {
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
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

    public Long getAuthor() {
        return author;
    }

    public void setAuthor(Long author) {
        this.author = author;
    }

    public String getImage() {
        return image;
    }

    public void setImage(String image) {
        this.image = image;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        AdDTO adDTO = (AdDTO) o;
        return Objects.equals(id, adDTO.id) && Objects.equals(title, adDTO.title) && Objects.equals(price, adDTO.price) && Objects.equals(description, adDTO.description) && Objects.equals(author, adDTO.author) && Objects.equals(image, adDTO.image);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, title, price, description, author, image);
    }

    @Override
    public String toString() {
        return "AdDTO{" +
                "pk=" + id +
                ", title='" + title + '\'' +
                ", price=" + price +
                ", description='" + description + '\'' +
                ", author=" + author +
                ", image='" + image + '\'' +
                '}';
    }



}
