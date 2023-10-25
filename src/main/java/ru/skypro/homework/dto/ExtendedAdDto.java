package ru.skypro.homework.dto;

import javax.validation.constraints.*;

public record ExtendedAdDto(
        //id объявления
        Integer pk,
        @NotBlank(message = "Имя автора не может быть пустым")
        @Size(min = 2, max = 10, message = "Имя автора должно быть от 2 до 10 символов")
        String authorFirstName,
        @NotBlank(message = "Фамилия автора не может быть пустым")
        @Size(min = 2, max = 10, message = "Фамилия автора должно быть от 2 до 10 символов")
        String authorLastName,
        @NotBlank(message = "Описание объявления не может быть пустым")
        @Size(min = 8, max = 64, message = "Описание объявления должен содержать не менее 8 и не более 64 символов")
        String description,
        @Email(message = "Некорректный формат адреса электронной почты")
        String email,
         //ссылка на картинку объявления
        String image,
        @NotBlank(message = "Введите номер Вашего контактного телефона")
        @Pattern(regexp = "\\+7\\s?\\(?\\d{3}\\)?\\s?\\d{3}-?\\d{2}-?\\d{2}",
                message = "Введите номер в формате +7 (000) 000-00-00")
        String phone,
        @NotNull(message = "Цена в объявлений не может быть пустой")
        @Min(0)
        @Max(10000000)
        Integer price,
        @NotBlank(message = "Заголовок объявления не может быть пустым")
        @Size(min = 4, max = 32, message = "Заголовок объявления должен содержать не менее 4 и не более 32 символов")
        String title

) {
}
