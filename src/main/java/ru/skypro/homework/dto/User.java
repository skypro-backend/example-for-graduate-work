package ru.skypro.homework.dto;

import lombok.*;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Pattern;
import javax.validation.constraints.Size;

@Getter
@Setter
@EqualsAndHashCode
@ToString
@AllArgsConstructor
@NoArgsConstructor
public class User {

    private int id;

    @NotBlank(message = "Это поле не может быть пустым")
    @Size(min = 4, max = 32, message = "Логин должен содержать от 4 до 32 символов")
    @Email
    private String email;

    @NotBlank(message = "Это поле не может быть пустым")
    @Size(min = 2, max = 16, message = "Имя пользователя должно содержать не менее 2 и не более 16 символов")
    private String firstName;

    @NotBlank(message = "Это поле не может быть пустым")
    @Size(min = 2, max = 16, message = "Фамилия пользователя должна содержать не менее 2 и не более 16 символов")
    private String lastName;

    @NotBlank(message = "Это поле не может быть пустым")
    @Pattern(regexp = "\\+7\\s?\\(?\\d{3}\\)?\\s?\\d{3}-?\\d{2}-?\\d{2}",
            message = "Введите номер в формате +7 (000) 000-00-00")
    private String phone;

    private Role role;

    private String image;

}
