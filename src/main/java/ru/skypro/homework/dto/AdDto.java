package ru.skypro.homework.dto;

import java.util.Objects;

public class AdDto {
    private Integer authorId;
    private String image;
    private Integer pkId;
    private Integer price;
    private String title;

    public int getAuthorId() {
        return authorId;
    }

    public void setAuthorId(int authorId) {
        this.authorId = authorId;
    }

    public String getImage() {
        return image;
    }

    public void setImage(String image) {
        this.image = image;
    }

    public int getPkId() {
        return pkId;
    }

    public void setPkId(int pkId) {
        this.pkId = pkId;
    }

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

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        AdDto adDto = (AdDto) o;
        return authorId == adDto.authorId && pkId == adDto.pkId && price == adDto.price && Objects.equals(image, adDto.image) && Objects.equals(title, adDto.title);
    }

    @Override
    public int hashCode() {
        return Objects.hash(authorId, image, pkId, price, title);
    }
}
