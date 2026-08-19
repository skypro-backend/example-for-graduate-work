package ru.skypro.homework.dto;
import io.swagger.v3.oas.annotations.media.Schema;
import ru.skypro.homework.model.Role;
import lombok.Data;

@Data
@Schema(description = "профиль пользователя")
public class User {

    @Schema(description = "id пользователя")
    private Long id;

    @Schema(description = "логин пользователя", example = "user@gmail.com")
    private String email;

    @Schema(description = "имя пользователя", example = "Ivan")
    private String firstName;

    @Schema(description = "фамилия пользователя", example = "Ivanov")
    private String lastName;

    @Schema(description = "телефон пользователя", example = "+79991234567")
    private String phone;

    @Schema(description = "роль пользователя")
    private Role role;

    @Schema(description = "ссылка на аватар пользователя")
    private String image;
}