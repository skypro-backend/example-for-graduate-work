package ru.skypro.homework.dto;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;

import javax.validation.constraints.NotBlank;

@Data
@Schema(description = "Пользователь")
public class UserDto {

    @Schema(description = "id пользователя ")
    @NotBlank
    private Integer id;

    @Schema(description = "логин пользователя ")
    @NotBlank
    private String email;

    @Schema(description = "имя пользователя ")
    @NotBlank
    private String firstName;
    @Schema(description = "фамилия пользователя ")
    @NotBlank
    private String lastName;

    @Schema(description = "телефон пользователя ")
    @NotBlank
    private String phone;

    @Schema(description = "роль пользователя")
    @NotBlank
    private Role role;

    @Schema(description = "ссылка на аватар пользователя ")
    @NotBlank
    private String image;
}
