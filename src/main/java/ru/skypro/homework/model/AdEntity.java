package ru.skypro.homework.model;

import lombok.Data;

import javax.persistence.*;
import java.util.Collection;


@Entity
@Data
@Table(name = "ads")
public class AdEntity {
    @Id
    private int id;
    private String image;
    private int price;
    private String title;

    @ManyToOne
    private UserEntity author;
    @OneToMany
    private Collection<CommentEntity> comments;
}
