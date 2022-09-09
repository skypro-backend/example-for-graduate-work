package ru.skypro.homework.models.entity;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;

@Entity
@Getter
@Setter
public class Ads {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer pk;
    private Integer price;
    private String title;
    private String description;

    @Column(name = "image_id")
    private Integer image;
    @Column(name = "author_id")
    private Integer author;
}
