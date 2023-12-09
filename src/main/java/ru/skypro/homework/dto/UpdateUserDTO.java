package ru.skypro.homework.dto;

import io.swagger.v3.oas.annotations.media.Schema;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Pattern;
import javax.validation.constraints.Size;

import lombok.Data;

@Data
@Schema(description = "Обновление информации об авторизованном пользователе")
public class UpdateUserDTO {
    @Schema(description = "имя пользователя")
    @NotBlank
    @Size(min = 1, max = 20)
    private String firstName;
    @Schema(description = "фамилия пользователя")
    @NotBlank
    @Size(min = 1, max = 20)
    private String lastName;
    @Schema(description = "номер телефона")
    @NotBlank
    @Pattern(regexp = "\\+7\s?\\(?\\d{3}\\)?\s?\\d{3}-?\\d{2}-?\\d{2}")
    private String phone;
}
