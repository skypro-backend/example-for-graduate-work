package ru.skypro.homework.dto;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;

@Data
@Schema(description = "Создание или обновление комментарий")
public class CreateOrUpdateComment {


    @Schema(description = "Текст комментария")
    @Size(min = 8,max = 64)
    @NotBlank(message ="Введите описание объявления")
    private String text;


}
