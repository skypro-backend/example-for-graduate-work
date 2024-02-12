package ru.skypro.homework.dto;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;

import javax.validation.constraints.Pattern;
import javax.validation.constraints.Size;

/**
 * UpdateUser  обновление данных пользователя
 * <br><i>содержит следующие поля:</i>
 * <br>- firstName <i>(имя пользователя)</i>;
 * <br>- lastName <i>(фамилия пользователя)</i>;
 * <br>- phone <i>(телефон пользователя)</i>;
 */
@Data
public class UpdateUser {
    @Size(min = 3, max = 10)
    @Schema(description = "имя пользователя")
    String firstName;

    @Size(min = 3, max = 10)
    @Schema(description = "фамилия пользователя")
    String lastName;

    @Pattern(regexp = "\\+7\\s?\\(?\\d{3}\\)?\\s?\\d{3}-?\\d{2}-?\\d{2}")
    @Schema(description = "телефон пользователя", example = "+7(987)654-32-10")//example = "+7(987)654-32-10"
    String phone;
}
