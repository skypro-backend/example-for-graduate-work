package ru.skypro.homework.dto;

import io.swagger.v3.oas.annotations.media.Schema;

@Schema(description = "Краткая информация об объявлении")
public class Ad {

    @Schema(description = "id автора объявления",
            example = "1")
    private int author;

    @Schema(description = "ссылка на картинку объявления",
            example = "/images/ad_1.jpg")
    private String image;

    @Schema(description = "id объявления",
            example = "100")
    private int pk;

    @Schema(description = "цена объявления",
            example = "50000")
    private int price;

    @Schema(description = "заголовок объявления",
            example = "Продам iPhone 13")
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
