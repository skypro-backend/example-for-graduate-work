package ru.skypro.homework.dto;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import ru.skypro.homework.dto.Role;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class User {

    @Schema(description = "Уникальный ID пользователя", example = "101")
    private Integer id;

    @Schema(description = "Email пользователя", example = "ivan.petrov@example.com")
    private String email;

    @Schema(description = "Имя пользователя", example = "Иван")
    private String firstName;

    @Schema(description = "Фамилия пользователя", example = "Петров")
    private String lastName;

    @Schema(description = "Телефон пользователя  формате +7...", example = "+79001112233")
    private String phone;

    @Schema(description = "Роль пользователя", example = "USER")
    private String role;

    @Schema(description = "Ссылка на аватар пользователя", example = "https://cdn.example.com/avatars/101.jpg")
    private String image;
}
