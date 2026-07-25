package com.skypro.dto;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

/**
 * DTO для ответа со списком комментариев.
 * <p>
 * Содержит общее количество комментариев и список объектов {@link Comment}.
 * Используется в эндпоинте {@code GET /ads/{adId}/comments}.
 * </p>
 */

@Data
@AllArgsConstructor
@NoArgsConstructor
public class Comments {

    @Schema(description = "общее количество комментариев")
    private Integer count;

    @Schema(description = "список комментариев")
    private List<Comment> results;
}
