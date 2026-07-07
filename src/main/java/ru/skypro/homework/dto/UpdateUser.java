package ru.skypro.homework.dto;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import jakarta.validation.constraints.Pattern;
import jakarta.validation.constraints.Size;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class UpdateUser {

    @Size(min = 3, max = 10)
    @Schema(description = "Имя пользователя (3-10 символов)", example = "Анна")
    private String firstName;

    @Size(min = 3, max = 10)
    @Schema(description = "Фамилия пользователя (3-10 символов)", example = "Смирнова")
    private String lastName;

    @Pattern(regexp = "\\+7\\s?\\(?\\d{3}\\)?\\s?\\d{3}-?\\d{2}-?\\d{2}", message = "Неверный формат телефона")
    @Schema(description = "Телефон в формате +7...", example = "+79991234567")
    private String phone;
}
