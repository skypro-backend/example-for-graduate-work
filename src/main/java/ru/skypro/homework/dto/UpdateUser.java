package ru.skypro.homework.dto;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;

import javax.validation.constraints.Pattern;
import javax.validation.constraints.Size;

@Data
@Schema(description = "данные для обновления пользователя")
public class UpdateUser {

    @Schema(description = "имя пользователя", example = "Ivan")
    @Size(min = 3, max = 10)
    private String firstName;

    @Schema(description = "фамилия пользователя", example = "Ivanov")
    @Size(min = 3, max = 10)
    private String lastName;

    @Schema(description = "телефон пользователя", example = "+79991234567")
    @Pattern(regexp = "\\+7\\s?\\(?\\d{3}\\)?\\s?\\d{3}-?\\d{2}-?\\d{2}")
    private String phone;
}
