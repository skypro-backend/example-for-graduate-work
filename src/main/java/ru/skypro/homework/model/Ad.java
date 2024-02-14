package ru.skypro.homework.model;

import javax.persistence.*;

@Entity
@Table(name = "ads")
public class Ad {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false)
    private Long author;

    @Column(nullable = false)
    private String image;

    @Column(nullable = false)
    private Integer price;

    @Column(nullable = false)
    private String title;

    @Column(nullable = false)
    private String description;

}
