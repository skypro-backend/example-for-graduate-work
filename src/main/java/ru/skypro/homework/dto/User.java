package ru.skypro.homework.dto;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Schema(description = "Информация о пользователе")
public class User {

    @Schema(
            description = "Идентификатор пользователя",
            example = "1"
    )
    private Integer id;

    @Schema(
            description = "Логин пользователя",
            example = "user@mail.ru"
    )
    private String email;

    @Schema(
            description = "Имя пользователя",
            example = "Иван"
    )
    private String firstName;

    @Schema(
            description = "Фамилия пользователя",
            example = "Иванов"
    )
    private String lastName;

    @Schema(
            description = "Телефон пользователя",
            example = "+7 (999) 123-45-67"
    )
    private String phone;

    @Schema(
            description = "Роль пользователя",
            example = "USER"
    )
    private Role role;

    @Schema(
            description = "Ссылка на аватар пользователя",
            example = "/images/avatar.jpg"
    )
    private String image;
}
