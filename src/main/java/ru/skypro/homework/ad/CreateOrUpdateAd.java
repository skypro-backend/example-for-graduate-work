package ru.skypro.homework.ad;

import io.swagger.v3.oas.annotations.media.Schema;

import javax.validation.constraints.Min;
import javax.validation.constraints.Size;

@Schema(description = "Создание и редактирование объявлений")
public class CreateOrUpdateAd {

    @Schema(description = "Заголовок", example = "Велосипед")
    @Size(min = 4, max = 32)
    private String title;

    @Schema(description = "Описание", example = "Практически новый")
    @Size(min = 8, max = 64)
    private String description;

    @Schema(description = "Цена", example = "100")
    @Min(0) // <<< Валидация
    private Integer price;

    @Schema(description = "Ссылка на картинку", example = "https://...")
    private String image;

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
}