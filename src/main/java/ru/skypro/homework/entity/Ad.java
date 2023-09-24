package ru.skypro.homework.entity;

import lombok.Data;
import javax.persistence.*;
@Data
@Entity
public class Ad {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer pk;
    @ManyToOne
    //id автора объявления
    private User user;
    //ссылка на картинку объявления
    private String image;
    //цена объявления
    private Integer price;
    //заголовок объявления
    private String title;
}