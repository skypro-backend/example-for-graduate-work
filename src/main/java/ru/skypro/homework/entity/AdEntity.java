package ru.skypro.homework.entity;

import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

public class AdEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;
    private UserEntity author;
    private String image;
    private int price;
    private String title;
    private String description;
}
