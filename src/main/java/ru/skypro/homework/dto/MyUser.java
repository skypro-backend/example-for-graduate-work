package ru.skypro.homework.dto;

import lombok.*;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;

@Getter
@Setter
@EqualsAndHashCode
@ToString
@AllArgsConstructor
@NoArgsConstructor
public class MyUser {

    @NotBlank(message = "Это поле не может быть пустым")
    @Size(min = 4, max = 32, message = "Логин должен содержать от 4 до 32 символов")
    @Email
    private String username;

    @NotBlank(message = "Это поле не может быть пустым")
    @Size(min = 8, max = 16, message = "Пароль должен содержать от 8 до 16 символов")
    private String password;

    private Role role;

}
