package ru.skypro.homework.dto.comments.in;

import lombok.Data;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.Size;

@Data
public class CreateOrUpdateCommentDto {
    @NotEmpty(message = "Поле не может быть пустым")
    @Size(min = 8, max = 64, message = "Комментарий не может быть меньше 8 и больше 64 символов")
    private String text;
}
