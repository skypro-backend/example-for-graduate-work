package ru.skypro.homework.dto.comments;

import lombok.Data;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.Size;

@Data
public class CreateOrUpdateCommentDto {
    @NotEmpty(message = "Поле не может быть пустым")
    @Size(min = 8, max = 64, message = "Ограничение на количество символов: от 8 до 64")
    private String text;

}
