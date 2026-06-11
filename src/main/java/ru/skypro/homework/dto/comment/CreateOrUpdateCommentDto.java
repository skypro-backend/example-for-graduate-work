package ru.skypro.homework.dto.comment;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Schema(name = "CreateOrUpdateComment", requiredProperties = {"text"})
public class CreateOrUpdateCommentDto {

    @Schema(description = "текст комментария", requiredMode = Schema.RequiredMode.REQUIRED, minLength = 8, maxLength = 64)
    private String text;
}
