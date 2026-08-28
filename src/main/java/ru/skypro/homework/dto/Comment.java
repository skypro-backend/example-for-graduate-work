package ru.skypro.homework.dto;


import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;

/**
 * DTO для информации о комментарии.
 * Используется для передачи данных комментария при получении, создании и обновлении.
 * Основные функции:
 * - хранение полной информации о комментарии (текст, автор, дата, ID);
 * - содержит данные об авторе (ID, имя, ссылка на аватар).
 */
@Data
@Schema(description = "Информация о комментарии")
public class Comment {

    @Schema(description = "ID автора комментария")
    private Integer author;

    @Schema(description = "Ссылка на аватар автора")
    private String authorImage;

    @Schema(description = "Имя автора комментария")
    private String authorFirstName;

    @Schema(description = "Дата и время создания комментария в миллисекундах")
    private Long createdAt;

    @Schema(description = "ID комментария")
    private Integer pk;

    @Schema(description = "Текст комментария")
    private String text;
}
