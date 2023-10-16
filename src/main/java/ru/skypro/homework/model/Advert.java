package ru.skypro.homework.model;

import lombok.Data;

import javax.persistence.*;
import java.util.List;

@Data
@Entity(name = "adverts")
public class Advert {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    @ManyToOne
    @JoinColumn(name = "user_id", referencedColumnName = "id")
    private User author;

    private int price;
    private String title;
    private String image;
    private String description;

    @OneToMany(mappedBy = "advert", cascade = CascadeType.ALL)
    private List<Comment> comments;
}