package ru.skypro.homework.dto;


import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;

import javax.validation.constraints.Pattern;
import javax.validation.constraints.Size;

@Data
@Schema(description = "Объект для обновления информации о пользователе")
public class UpdateUser {

    @Schema(description = "Имя пользователя", minLength = 3, maxLength = 10)
    @Size(min = 3, max = 10)
    private String firstName;

    @Schema(description = "Фамилия пользователя", minLength = 3, maxLength = 10)
    @Size(min = 3, max = 10)
    private String lastName;

    @Schema(description = "Телефон пользователя", pattern = "\\+7\\s?\\(?\\d{3}\\)?\\s?\\d{3}-?\\d{2}-?\\d{2}")
    @Pattern(regexp = "\\+7\\s?\\(?\\d{3}\\)?\\s?\\d{3}-?\\d{2}-?\\d{2}")
    private String phone;
}
