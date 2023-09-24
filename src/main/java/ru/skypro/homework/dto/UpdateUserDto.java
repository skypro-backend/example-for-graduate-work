package ru.skypro.homework.dto;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;

@AllArgsConstructor
@EqualsAndHashCode
@Data
public class UpdateUserDto {

    @Schema(description = "Имя пользователя")
    public String firstName;

    @Schema(description = "Фамилия пользователя")
    public String lastName;

    @Schema(description = "Телефон пользователя")
    public String phone;
}
