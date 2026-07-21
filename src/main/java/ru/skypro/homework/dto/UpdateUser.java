package ru.skypro.homework.dto;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;

import javax.validation.constraints.Pattern;
import javax.validation.constraints.Size;

@Data
@Schema(description = "Данные для обновления информации о пользователе")
public class UpdateUser {

    @Schema(description = "Имя пользователя")
    @Size(min = 3, max = 10)
    private String firstName;

    @Schema(description = "Фамилия пользователя")
    @Size(min = 3, max = 10)
    private String lastName;

    @Schema(description = "Телефон пользователя")
    @Pattern(
            regexp = "\\+7\\s?(\\(\\d{3}\\))?\\s?\\d{3}-?\\d{2}-?\\d{2}"
    )
    private String phone;
}