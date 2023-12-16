package ru.skypro.homework.model;

import lombok.Data;

import javax.persistence.*;


@Entity
@Data
@Table(name = "ads")
public class Ad {
    @Id
    int id;
    String image;
    int price;
    String title;

    @ManyToOne
    User author;
    //Collection<Comment> comments;
}
