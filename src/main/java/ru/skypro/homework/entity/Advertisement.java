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

    @ManyToOne()
    private String author;
    private String authorFirstName;
    private String authorLastName;
    private String authorImage;

    @OneToOne
    private String title;
    private String description;
    @OneToOne
    private String image;
    private Integer price;
}
