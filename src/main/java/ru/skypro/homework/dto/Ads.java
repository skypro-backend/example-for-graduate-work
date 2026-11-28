package ru.skypro.homework.dto;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;

import java.util.List;

@Data
@Schema(description = "Список объявлений")
public class Ads {
    @Schema(description = "общее количество объявлений", example = "10")
    private Integer count;

    @Schema(description = "список объявлений")
    private List<Ad> results;
}