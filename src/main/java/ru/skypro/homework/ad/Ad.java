package ru.skypro.homework.ad;

import io.swagger.v3.oas.annotations.media.Schema;

@Schema(description = "Объявление")
public class Ad {

    @Schema(description = "Код объявления", example = "102356")
    private Integer pk;

    @Schema(description = "Заголовок объявления", example = "Велосипед")
    private String title;

    @Schema(description = "Цена товара", example = "100")
    private Integer price;

    @Schema(description = "Фото товара", example = "-картинка-")
    private String image;

    @Schema(description = "Имя пользователя", example = "Иван")
    private Integer author;

    public Integer getPk() {
        return pk;
    }

    public void setPk(Integer pk) {
        this.pk = pk;
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

    public String getImage() {
        return image;
    }

    public void setImage(String image) {
        this.image = image;
    }

    public Integer getAuthor() {
        return author;
    }

    public void setAuthor(Integer author) {
        this.author = author;
    }
}