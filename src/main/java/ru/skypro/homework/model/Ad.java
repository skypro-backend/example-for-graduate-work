package ru.skypro.homework.model;

import lombok.Data;

import javax.persistence.*;

@Entity
@Data
@Table(name = "Объявления")
public class Ad {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long pk;
    private int author;
    private String image;
    private int price;
    private String title;
}
