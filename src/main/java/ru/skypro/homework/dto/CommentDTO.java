package ru.skypro.homework.dto;

import io.swagger.v3.oas.annotations.media.Schema;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

import lombok.Data;

@Data
@Schema(description = "комментарий к объявлению")
public class CommentDTO {
    @Schema(description = "id создателя комментария")
    private int author;
    @Schema(description = "ссылка на аватар автора комментария")
    @NotBlank
    private String authorImage;
    @Schema(description = "имя создателя комментария")
    @NotNull
    @Size(min = 1, max = 20)
    private String authorFirstName;
    @Schema(description = "время создания комментария")
    private long createdAt;
    @Schema(description = "id комментария")
    private int pk;
    @Schema(description = "текст комментария")
    @NotBlank
    @Size(min = 4, max = 100)
    private String text;

}
