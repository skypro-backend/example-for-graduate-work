package ru.skypro.homework.dto;

import io.swagger.v3.oas.annotations.media.Schema;

import javax.validation.constraints.*;

import lombok.Data;
import ru.skypro.homework.model.Image;

@Data
@Schema(description = "Добавление комментария к объявлению")
public class ExtendedAdDTO {
    @Schema(description = "id объявления")
    private int pk;
    @Schema(description = "имя автора объявления")
    @NotBlank
    @Size(min = 1, max = 20)
    private String firstName;
    @Schema(description = "фамилия автора объявления")
    @NotBlank
    @Size(min = 1, max = 20)
    private String lastName;
    @Schema(description = "текст объявления")
    @NotBlank
    @Size(min = 4, max = 100)
    private String description;
    @Schema(description = "адрес электронной почты автора объявления")
    @NotBlank
    @Email
    private String email;
    @Schema(description = "ссылка на аватар автора объявления")
    @NotBlank
    private String image;
    @Schema(description = "номер телефона автора объявления")
    @NotBlank
    @Pattern(regexp = "\\+7\s?\\(?\\d{3}\\)?\s?\\d{3}-?\\d{2}-?\\d{2}")
    private String phone;
    @Schema(description = "цена объявления")
    @NotNull
    @Positive
    private int price;
    @Schema(description = "заголовок объявления")
    @NotBlank
    @Size(min = 4, max = 32)
    private String title;
}