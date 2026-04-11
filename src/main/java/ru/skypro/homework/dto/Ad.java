package ru.skypro.homework.dto;

import io.swagger.v3.oas.annotations.media.Schema;

@Schema(description = "Краткая информация об объявлении")
public class Ad {

    @Schema(description = "id автора объявления")
    private int author;

    @Schema(description = "ссылка на картинку объявления")
    private String image;

    @Schema(description = "id объявления")
    private int pk;

    @Schema(description = "цена объявления")
    private int price;

    @Schema(description = "заголовок объявления")
    private String title;

    public Ad() {
    }
    public Ad(int author, String image, int pk, int price, String title) {
        this.author = author;
        this.image = image;
        this.pk = pk;
        this.price = price;
        this.title = title;
    }

    public int getAuthor() {
        return author;
    }

    public String getImage() {
        return image;
    }

    public int getPk() {
        return pk;
    }

    public int getPrice() {
        return price;
    }

    public String getTitle() {
        return title;
    }

    public void setAuthor(Integer author) {
        this.author = author;
    }

    public void setImage(String image) {
        this.image = image;
    }

    public void setPk(int pk) {
        this.pk = pk;
    }

    public void setPrice(int price) {
        this.price = price;
    }

    public void setTitle(String title) {
        this.title = title;
    }
}
