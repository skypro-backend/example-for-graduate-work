package ru.skypro.homework.dto;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;

public record CreateOrUpdateCommentDto(
        @NotBlank(message = "Текст комментария не может быть пустым")
        @Size(min = 2, max = 64, message = "Текст комментария должно быть от 8 до 64 символов")
        String text
) {
}
