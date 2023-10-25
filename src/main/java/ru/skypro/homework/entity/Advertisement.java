package ru.skypro.homework.entity;

import lombok.Data;

import javax.persistence.*;

@Entity
@Data
@Table(name = "advertisement")
public class Advertisement {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @ManyToOne
    @JoinColumn(name = "user_id")
    @Column(name = "author")
    private String author;
    @JoinColumn(name = "first_name")
    @Column(name = "first_name")
    private String authorFirstName;
    @JoinColumn(name = "last_name")
    @Column(name = "last_name")
    private String authorLastName;
    @Column(name = "author_image")
    @JoinColumn(name = "user_image")
    private String authorImage;
    @Column(name = "title")
    private String title;
    @Column(name = "description")
    private String description;
    @Column(name = "image")
    private String image;
    @Column(name = "price")
    private Integer price;
}
