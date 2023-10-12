package ru.skypro.homework.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import ru.skypro.homework.service.impl.AdsService;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class AdsDto  {
    private Integer author; // id автора объявления
    private String image; // ссылка на картинку объявления
    private Integer pk; // id объявления
    private Integer price; // цена объявления
    private String title; // заголовок объявления
}
