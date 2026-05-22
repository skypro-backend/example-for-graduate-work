package ru.skypro.homework.dto.comment;

import io.swagger.v3.oas.annotations.media.Schema;

@Schema(description = "Информация о комментарии")
public class CommentDto {

    @Schema(description = "ID автора комментария", example = "5")
    private Long author;

    @Schema(description = "URL аватара автора", example = "/images/avatars/5.jpg")
    private String authorImage;

    @Schema(description = "Имя автора", example = "Анна Смирнова")
    private String authorFirstName;

    @Schema(description = "Дата создания комментария (в миллисекундах)", example = "1698765432000")
    private Long createdAt;

    @Schema(description = "ID комментария", example = "42")
    private Long pk;

    @Schema(description = "Текст комментария", example = "Отличное объявление!", maxLength = 500)
    private String text;
}
