package ru.skypro.homework.dto;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Pattern;
import javax.validation.constraints.Size;

@Data
@Schema(description = "обновление данных пользователя")
public class UpdateUserDto {
    @Schema(description = "имя пользователя")
    @NotBlank
    @Size(min = 3, max = 10)
    private String firstName;

    @Schema(description = "фамилия пользователя")
    @NotBlank
    @Size(min = 3, max = 10)
    private String lastName;

    @Schema(description = "телефон пользователя")
    @Pattern(regexp = "\\+7\\s?\\(?\\d{3}\\)?\\s?\\d{3}-?\\d{2}-?\\d{2}")
    private String phone;

}
