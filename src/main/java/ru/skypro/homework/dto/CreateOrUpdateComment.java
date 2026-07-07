package ru.skypro.homework.dto;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class CreateOrUpdateComment {

    @NotNull
    @Size(min = 8, max = 64)
    @Schema(description = "Текст комментария (8-64 символа)", example = "Товар полностью соответствует описанию, продавец вежливый.")
    private String text;
}