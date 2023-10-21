package ru.skypro.homework.dto;

public record CreateOrUpdateCommentDto(
        /**
         minLength: 8
         maxLength: 64
         текст комментария
         */
        String text
) {
}
