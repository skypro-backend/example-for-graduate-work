package ru.skypro.homework.dto.ad;

import io.swagger.v3.oas.annotations.media.Schema;

import java.util.List;

@Schema(description = "Список объявлений")
public record AdsDto(
        @Schema(description = "общее количество объявлений") Integer count,
        @Schema(description = "список объявлений") List<AdDto> results
) {
}
