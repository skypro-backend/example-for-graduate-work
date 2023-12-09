package ru.skypro.homework.dto;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.*;

import javax.validation.constraints.NotBlank;


@Data
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@ToString
@Schema(description = "Комментарии ")
public class CommentDto {

    @Schema(description = "id автора комментария ")
    @NotBlank
    private Integer author;

    @Schema(description = "ссылка на аватар автора комментария ")
    @NotBlank
    private String authorImage;

    @Schema(description = "имя создателя комментария")
    @NotBlank
    private String authorFirstName;

    @Schema(description = "дата и время создания комментария в миллисекундах с 00:00:00 01.01.1970 ")
    @NotBlank
    private Long createdAt;

    @Schema(description = "id комментария ")
    @NotBlank
    private Integer pk;

    @Schema(description = "текст комментария ")
    @NotBlank
    private String text;


}
