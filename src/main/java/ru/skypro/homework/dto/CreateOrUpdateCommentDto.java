package ru.skypro.homework.dto;

import lombok.Data;
import io.swagger.v3.oas.annotations.media.Schema;

import javax.validation.constraints.Size;
import javax.validation.constraints.NotBlank;
@Data
@Schema(description = "Создание или обновление комментарий")
public class CreateOrUpdateCommentDto {

    @Schema(description = "Текст комментария")
    @Size(min = 8,max = 64)
    @NotBlank(message ="Введите описание объявления")
    private String text;
}
