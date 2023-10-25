package ru.skypro.homework.entity;

import javax.persistence.*;
import java.math.BigInteger;

@Entity
@Table(name = "advertisement")
public class Advertisement {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "pk")
    private Long id;
    @Column(name = "author")
    @JoinColumn(name = "user_id")
    private BigInteger author;
    @Column(name = "author_first_name")
    @JoinColumn(name = "author_first_name")
    private BigInteger authorFirstName;
    @Column(name = "author_last_name")
    @JoinColumn(name = "author_last_name")
    private BigInteger authorLastName;
    @Column(name = "author_image")
    @JoinColumn(name = "user_image")
    private String authorImage;
    private String title;
    private Integer price;
    private String description;
    private String image;
}
