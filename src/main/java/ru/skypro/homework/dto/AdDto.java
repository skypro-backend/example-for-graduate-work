package ru.skypro.homework.dto;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;


@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Schema(description = "Краткая информация об объявлении")
public class AdDto {

    @Schema(description = "ID автора объявления", example = "1")
    private Integer author;

    @Schema(description = "Ссылки на картинки объявления")
    private List<String> image;

    @Schema(description = "ID объявления", example = "1")
    private Integer pk;

    @Schema(description = "Цена объявления", example = "15000")
    private Integer price;

    @Schema(description = "Заголовок объявления", example = "Продам гараж")
    private String title;
}