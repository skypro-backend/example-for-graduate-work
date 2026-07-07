package ru.skypro.homework.dto;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class Comment {

    @Schema(description = "ID автора комментария", example = "105")
    private Integer author;

    @Schema(description = "Ссылка на аватар автора", example = "https://cdn.skypro.ru/avatars/user_105.jpg")
    private String authorImage;

    @Schema(description = "Имя автора комментария", example = "Анна")
    private String authorFirstName;

    @Schema(description = "Дата и время создания комментария (Unix timestamp в миллисекундах)", example = "1715623456789")
    private Long createdAt;

    @Schema(description = "ID комментария", example = "999")
    private Integer pk;

    @Schema(description = "Текст комментария", example = "Товар отличный, доставка быстрая!")
    private String text;
}

