package ru.skypro.homework.dto;

import com.fasterxml.jackson.annotation.JsonProperty;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;

@AllArgsConstructor
@EqualsAndHashCode
@Data
public class User {
    @Schema(description = "id пользователя")
    public long id;

    @Schema(description = "Имя пользователя")
    public String firstName;

    @Schema(description = "Фамилия пользователя")
    public String lastName;

    @Schema(description = "email пользователя")
    public String email;

    @Schema(description = "Телефон пользователя")
    public String phone;

    @JsonProperty(value = "image")
    @Schema(description = "Путь на аватар пользователя")
    public String avatarPath;

}
