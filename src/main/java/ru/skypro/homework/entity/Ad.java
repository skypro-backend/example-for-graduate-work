package ru.skypro.homework.entity;

import lombok.Data;
import javax.persistence.*;
@Data
@Entity
public class Ad {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer pk;  //id объявления
    @ManyToOne
    private User user; //id автора объявления
    private String image; //ссылка на картинку объявления
    private Integer price; //цена объявления
    private String title; //заголовок объявления
    private String description; // описание
}