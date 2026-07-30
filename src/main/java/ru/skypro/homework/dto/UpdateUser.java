package ru.skypro.homework.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Pattern;
import javax.validation.constraints.Size;

/**
 * Данные изменения профиля пользователя.
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
public class UpdateUser {

    @NotBlank
    @Size(min = 3, max = 10)
    private String firstName;

    @NotBlank
    @Size(min = 3, max = 10)
    private String lastName;

    @NotBlank
    @Pattern(
            regexp = "\\+7\\s?\\(?\\d{3}\\)?\\s?\\d{3}-?\\d{2}-?\\d{2}"
    )
    private String phone;
}
