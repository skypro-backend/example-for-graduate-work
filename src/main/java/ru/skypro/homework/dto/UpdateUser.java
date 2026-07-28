package ru.skypro.homework.dto;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Pattern;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Schema(description = "Данные для обновления профиля пользователя")
public class UpdateUser {

    @NotBlank
    @Size(min = 3, max = 10)
    @Schema(
            description = "Имя пользователя",
            example = "Иван"
    )
    private String firstName;

    @NotBlank
    @Size(min = 3, max = 10)
    @Schema(
            description = "Фамилия пользователя",
            example = "Иванов"
    )
    private String lastName;

    @NotBlank
    @Pattern(regexp = "\\+7\\s?\\(?\\d{3}\\)?\\s?\\d{3}-?\\d{2}-?\\d{2}")
    @Schema(
            description = "Телефон пользователя",
            example = "+7 (999) 123-45-67"
    )
    private String phone;
}
