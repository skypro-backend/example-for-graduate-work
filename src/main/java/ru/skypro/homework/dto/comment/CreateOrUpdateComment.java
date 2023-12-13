package ru.skypro.homework.dto.comment;

import lombok.Data;

import javax.validation.constraints.Size;

@Data
public class CreateOrUpdateComment {

    @Size(min = 8, max = 64, message = "Проверьте количество символов.")
    private String text;
}
