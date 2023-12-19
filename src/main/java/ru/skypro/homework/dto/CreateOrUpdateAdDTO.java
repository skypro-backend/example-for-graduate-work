package ru.skypro.homework.dto;

import lombok.Data;

@Data
public class CreateOrUpdateAdDTO {

    private String title;         //заголовок объявления
    private Integer price;            //цена объявления
    private String description;   //описание объявления

}
