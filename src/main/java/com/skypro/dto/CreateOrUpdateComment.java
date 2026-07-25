package com.skypro.dto;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * DTO для создания или обновления комментария.
 * <p>
 * Используется как тело запроса для {@code POST /ads/{adId}/comments}
 * и {@code PATCH /ads/{adId}/comments/{commentId}}.
 * </p>
 */

@Data
@AllArgsConstructor
@NoArgsConstructor
public class CreateOrUpdateComment {

    @Schema(description = "текст комментария", minLength = 8, maxLength = 64)
    private String text;
}
