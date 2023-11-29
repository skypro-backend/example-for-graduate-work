package ru.skypro.homework.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.Max;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.PositiveOrZero;
import javax.validation.constraints.Size;

@NoArgsConstructor
@AllArgsConstructor
@Data
public class AdDTO {

    @NotBlank(message = "Обязательное поле")
    private int author; //id автора объявления

    // потом раскомментить
//    @NotBlank
    private String image;// ссылка на картинку объявления

    @NotBlank(message = "Обязательное поле")
    private int pk;

    @NotBlank(message = "Обязательное поле")
    @PositiveOrZero(message = "Цена не может быть меньше 0")
    @Max(value = 10000000, message = "Максимальная цена 10 млн")
    private int price;

    @NotBlank(message = "Обязательное поле")
    @Size(min = 4, max = 32, message = "Количество символов от 4 до 32")
    private String title;
}