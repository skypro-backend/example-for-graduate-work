package ru.skypro.homework.dto.user;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;
import ru.skypro.homework.dto.Role;

@Data
@Schema(description = "Информация о пользователе")
public class UserDto {

    @Schema(description = "id пользователя", example = "1")
    private int id;

    @Schema(description = "логин пользователя", example = "john_doe@example.com")
    private String email;

    @Schema(description = "имя пользователя", example = "John")
    private String firstName;

    @Schema(description = "фамилия пользователя", example = "Doe")
    private String lastName;

    @Schema(description = "телефон пользователя", example = "+7 123 456-78-90")
    private String phone;

    @Schema(description = "роль пользователя", allowableValues = {"USER", "ADMIN"}, example = "USER")
    private Role role;

    @Schema(description = "ссылка на аватар пользователя", example = "/images/avatar/1.jpg")
    private String image;
}