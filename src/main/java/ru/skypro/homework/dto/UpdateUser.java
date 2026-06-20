package ru.skypro.homework.dto;


import io.swagger.v3.oas.annotations.media.Schema;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Schema(description = "обновление пользователя")
public class UpdateUser {
    @Schema(description = "имя пользователя", example = "Ольга")
    private String firstName;
    @Schema(description = "фамилия пользователя", example = "Прохорова")
    private String lastName;
    @Schema(description = "телефон пользователя", example = "89051234567")
    private String phone;

}
