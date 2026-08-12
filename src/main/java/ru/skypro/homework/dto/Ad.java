package ru.skypro.homework.dto;
import lombok.Data;
import io.swagger.v3.oas.annotations.media.Schema;

@Data
@Schema(description = "информация об объявлении")
public class Ad {

    @Schema(description = "id автора объявления")
    private Long author;

    @Schema(description = "ссылка на картинку объявления")
    private String image;

    @Schema(description = "id объявления")
    private Long pk;

    @Schema(description = "цена объявления")
    private Long price;

    @Schema(description = "заголовок объявления")
    private String title;
}
