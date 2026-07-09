package ru.skypro.homework.dto;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Schema(description = "информация о комментарии")
public class Comment {
    @Schema(description = "id автора комментария", example = "1")
    private Integer author;
    @Schema(description = "ссылка на аватар автора комментария")
    private String authorImage;
    @Schema(description = "имя создателя комментария", example = "Ольга")
    private String authorFirstName;
    @Schema(description = "дата и время создания комментария в миллисекундах с 00:00:00 01.01.1970", example = "1718352000000")
    private Long createdAt;
    @Schema(description = "id комментария", example = "1")
    private Integer pk;
    @Schema(description = "текст комментария", example = "покупкой довольна")
    private String text;
}
