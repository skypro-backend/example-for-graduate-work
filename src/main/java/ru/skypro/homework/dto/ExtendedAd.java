package ru.skypro.homework.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;

@AllArgsConstructor
@EqualsAndHashCode
@Data
public class ExtendedAd {
    private Integer pk; // ($int32) id объявления
    private String authorFirstName; // имя автороа объявления
    private String authorLastName; // фамилия автора объявления
    private String description; // описание объявления
    private String email; // логин автора объявления
    private String image; // ссылка на картинку объявления
    private String phone; // телефон автора объявления
    private Integer price; // ($int32) цена объявления
    private String title; // заголовок объявления
}
