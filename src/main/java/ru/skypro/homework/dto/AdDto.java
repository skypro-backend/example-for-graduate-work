package ru.skypro.homework.dto;

import javax.validation.constraints.*;

public record AdDto(
        //id автора
        Integer author,
        //ссылка на картинку объявления
        String image,
        //ID объявления
        Integer pk,
        @NotNull(message = "Цена в объявлений не может быть пустой")
        @Min(0)
        @Max(10000000)
        Integer price,
        @NotBlank(message = "Заголовок объявления не может быть пустым")
        @Size(min = 4, max = 32, message = "Заголовок объявления должен содержать не менее 4 и не более 32 символов")
        String title
) {
}
