package ru.skypro.homework.model;

import lombok.Data;

import javax.persistence.*;


@Entity
@Data
@Table(name = "ads")
public class AdEntity {
    @Id
    int id;
    String image;
    int price;
    String title;

    @ManyToOne
    UserEntity author;
    //Collection<Comment> comments;
}
