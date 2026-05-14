package ru.skypro.homework.dto.ads;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;

import java.util.List;

@Data
@Schema(description = "Список объявлений")
public class AdsDto {

    @Schema(description = "общее количество объявлений", example = "42")
    private int count;

    @Schema(description = "список объявлений")
    private List<AdDto> results;
}