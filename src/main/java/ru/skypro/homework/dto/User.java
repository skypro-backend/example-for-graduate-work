package ru.skypro.homework.dto;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;


@Data
@NoArgsConstructor
@AllArgsConstructor
@Schema(description = "информация о пользователе")
public class User {
    @Schema(description = "id пользователя", example = "1")
    private Integer id;
    @Schema(description = "логин пользователя", example = "Olga123@yandex.ru")
    private String email;
    @Schema(description = "имя пользователя", example = "Ольга")
    private String firstName;
    @Schema(description = "фамилия пользователя", example = "Прохорова")
    private String lastName;
    @Schema(description = "телефон пользователя", example = "89051234567")
    private String phone;
    @Schema(description = "роль пользователя")
    private Role role;
    @Schema(description = "ссылка на аватар пользователя")
    private String image;

}
