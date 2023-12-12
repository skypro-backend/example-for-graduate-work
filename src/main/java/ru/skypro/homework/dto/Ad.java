package ru.skypro.homework.dto;

import lombok.*;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class Ad {
    private int author; //id автора
    private String image;
    private int pk; //id объявления
    private int price;
    private String title;
}