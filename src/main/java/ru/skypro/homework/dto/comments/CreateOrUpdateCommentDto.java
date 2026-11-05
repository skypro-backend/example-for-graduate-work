package ru.skypro.homework.dto.comments;

import io.swagger.v3.oas.annotations.media.Schema;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;

@Schema(description = "данные добавления комментария к объявлению")
public record CreateOrUpdateCommentDto(
        @Schema(description = "текст комментария")
        @NotBlank
        @Size(min = 8, max = 64)
        String text
) {
}
