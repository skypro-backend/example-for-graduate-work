package ru.skypro.homework.dto;

import lombok.Data;
import io.swagger.v3.oas.annotations.media.Schema;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;

@Data
public class UpdateUserDto {


    @Schema(description = "Имя пользователя")
    @Size(min = 3,max = 10)
    @NotBlank(message ="Введите новое имя пользователя")
    private String firstName;


    @Schema(description = "Фамилия пользователя")
    @Size(min = 3,max = 10)
    @NotBlank(message ="Введите новую фамилию пользователя")
    private String lastName;


    @Schema(description = "\\+7\\s?\\(?\\d{3}\\)?\\s?\\d{3}-?\\d{2}-?\\d{2}")
    @NotBlank(message ="Введите новый телефон пользователя")
    private String phone;
}
