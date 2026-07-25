package com.skypro.dto;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

/**
 * DTO для ответа со списком объявлений.
 * <p>
 * Содержит общее количество объявлений и список объектов {@link Ad}.
 * Используется в эндпоинтах {@code GET /ads} и {@code GET /ads/me}.
 * </p>
 */

@Data
@AllArgsConstructor
@NoArgsConstructor
public class Ads {

    @Schema(description = "общее количество объявлений")
    private Integer count;

    @Schema(description = "список объявлений")
    private List<Ad> results;
}
