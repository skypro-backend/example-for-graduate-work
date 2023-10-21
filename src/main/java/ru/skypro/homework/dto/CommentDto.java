package ru.skypro.homework.dto;

public record CommentDto(
        /**
         id автора комментария
         */
        Integer author,
        /**
         ссылка на аватар автора комментария
         */
        String authorImage,
        /**
         имя создателя комментария
         */
        String authorFirstName,
        /**
         дата и время создания комментария в миллисекундах с 00:00:00 01.01.1970
         */
        Integer createdAt,
        /**
         id комментария
         */
        Integer pk,
        /**
         текст комментария
         */
        String text
) {
}
