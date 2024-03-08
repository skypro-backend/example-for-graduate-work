package ru.skypro.homework.entity;

import lombok.Data;

import javax.persistence.*;

@Data
@Entity
public class Ad {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer pk;
    private String image;
    private Integer price;
    private String title;
    private String description;

    @ManyToOne
    @JoinColumn
    private User user;
}
