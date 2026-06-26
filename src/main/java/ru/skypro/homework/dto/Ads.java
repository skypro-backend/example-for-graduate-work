package ru.skypro.homework.dto;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;
import java.util.List;

@Data
@Schema(description = "Список объявлений")
public class Ads {
    @Schema(description = "Общее количество объявлений")
    private Integer count;

    @Schema(description = "Коллекция объявлений")
    private List<AdDto> results;
}