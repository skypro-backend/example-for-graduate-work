package ru.skypro.homework.dto;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;

@Data
@Schema(description = "Data for creating or updating a comment")
public class CreateOrUpdateComment {

    @Schema(
            description = "Comment text",
            minLength = 8,
            maxLength = 64
    )
    private String text;
}