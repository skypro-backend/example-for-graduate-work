package ru.skypro.homework.dto;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;

import javax.validation.constraints.Size;

@Data
public class CreateOrUpdateComment {
    @Size(min = 8, max = 64)
    @Schema(description = "текст комментария")
    String text;
}
