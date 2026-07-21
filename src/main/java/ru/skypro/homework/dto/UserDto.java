package ru.skypro.homework.dto;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Schema(description = "Данные пользователя")
public class UserDto {

    @Schema(description = "ID пользователя", example = "1")
    private Integer id;

    @Schema(description = "Email пользователя", example = "user@example.com")
    private String email;

    @Schema(description = "Имя пользователя", example = "Иван")
    private String firstName;

    @Schema(description = "Фамилия пользователя", example = "Иванов")
    private String lastName;

    @Schema(description = "Телефон пользователя", example = "+79161234567")
    private String phone;

    @Schema(description = "Роль пользователя", example = "USER")
    private String role;

    @Schema(description = "Ссылка на аватар пользователя")
    private String image;
}
