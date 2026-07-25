package ru.skypro.homework.dto;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Schema(description = "Комментарий к объявлению")
public class Comment {

    @Schema(description = "ID автора комментария", example = "1")
    private Integer author;

    @Schema(description = "Ссылка на аватар автора", example = "/images/avatar.jpg")
    private String authorImage;

    @Schema(description = "Имя автора", example = "Иван")
    private String authorFirstName;

    @Schema(description = "Время создания в миллисекундах", example = "1712345678901")
    private Long createdAt;

    @Schema(description = "ID комментария", example = "5")
    private Integer pk;

    @Schema(description = "Текст комментария", example = "Очень интересное предложение")
    private String text;
}
