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

    @JoinColumn(name = "author_id")
    @ManyToOne
    private UserEntity author;
    @OneToMany(mappedBy = "author")
    private Collection<CommentEntity> comments;
}
