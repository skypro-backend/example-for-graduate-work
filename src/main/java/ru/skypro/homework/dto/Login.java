package ru.skypro.homework.dto;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;

import javax.validation.constraints.Size;

@Data
public class Login {
    /**
     * логин
     */
    @Size(min = 4, max = 32)
    @Schema(description = "логин")
    // есть несовпадение с общим представлением ,
    // до этого логин - это email пользователя,
    // сейчас ограничение от 4 до 32 символов
    // ... достаточно ли?
    private String username;


    /**
     * пароль
     */
    @Size(min = 8, max = 16)
    @Schema(description = "пароль")
    private String password;
}
