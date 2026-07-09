package ru.skypro.homework.dto;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;


@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Schema(description = "Комментарий к объявлению")
public class CommentDto {

    @Schema(description = "ID автора комментария", example = "1")
    private Integer author;

    @Schema(description = "Ссылка на аватар автора")
    private String authorImage;

    @Schema(description = "Имя автора", example = "Иван")
    private String authorFirstName;

    @Schema(description = "Дата и время создания (timestamp)", example = "1693500000000")
    private Long createdAt;

    @Schema(description = "ID комментария", example = "1")
    private Integer pk;

    @Schema(description = "Текст комментария", example = "Отличное объявление!")
    private String text;
}
