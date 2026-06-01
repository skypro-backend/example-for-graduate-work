package ru.skypro.homework.ad;


import io.swagger.v3.oas.annotations.media.Schema;

@Schema(description = "Создание и редактирование объявлений")
public class CreateOrUpdateAd {

    @Schema(description = "Заголовок", example = "Велосипед")
    private String title;

    @Schema(description = "Описание", example = "прекрасный товар, практически новый")
    private String description;

    @Schema(description = "Цена", example = "100")
    private Integer price;

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
}
