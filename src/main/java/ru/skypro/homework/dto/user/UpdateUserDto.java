package ru.skypro.homework.dto.user;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;

import javax.validation.constraints.Pattern;
import javax.validation.constraints.Size;

@Data
@Schema(description = "Данные для обновления информации о пользователе")
public class UpdateUserDto {

    @Schema(description = "имя пользователя", minLength = 3, maxLength = 10, example = "John")
    @Size(min = 3, max = 10)
    private String firstName;

    @Schema(description = "фамилия пользователя", minLength = 3, maxLength = 10, example = "Doe")
    @Size(min = 3, max = 10)
    private String lastName;

    @Schema(description = "телефон пользователя",
            pattern = "\\+7\\s?\\(?\\d{3}\\)?\\s?\\d{3}-?\\d{2}-?\\d{2}",
            example = "+7 123 456-78-90")
    @Pattern(regexp = "\\+7\\s?\\(?\\d{3}\\)?\\s?\\d{3}-?\\d{2}-?\\d{2}")
    private String phone;
}