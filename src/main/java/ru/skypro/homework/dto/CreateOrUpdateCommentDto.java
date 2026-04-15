package ru.skypro.homework.dto;

import lombok.Data;

import javax.validation.constraints.Size;

@Data
public class CreateOrUpdateCommentDto {

    @Size(min = 8, max = 64, message = "Текст комментария должен содержать от 8 до 64 символов")
    private String text;
}
