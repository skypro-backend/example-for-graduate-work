package ru.skypro.homework.dto;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;

@Data
@Schema(description = "Список объявлений")
public class Ads {
    @Schema(description = "общее количество объявлений")
    private Integer count;
    @Schema(description = "список объявлений")
    private java.util.List<Ad> results;
}