package ru.skypro.homework.dto;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;

import javax.validation.constraints.*;

@Data
@Schema(description = "Обновление информации о пользователе")
public class UpdateUser {
    @NotBlank
    @Size(min = 3, max = 10)
    @Schema(description = "имя пользователя", minLength = 3, maxLength = 10)
    private String firstName;

    @NotBlank
    @Size(min = 3, max = 10)
    @Schema(description = "фамилия пользователя", minLength = 3, maxLength = 10)
    private String lastName;

    @NotBlank
    @Pattern(regexp = "\\+7\\s?\\(?\\d{3}\\)?\\s?\\d{3}-?\\d{2}-?\\d{2}")
    @Schema(description = "телефон пользователя", pattern = "\\+7\\s?\\(?\\d{3}\\)?\\s?\\d{3}-?\\d{2}-?\\d{2}")
    private String phone;
}
