package ru.skypro.homework.entity;

import lombok.Data;

import javax.persistence.*;

@Entity(name = "comments")
@Data
public class CommentEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;
    private Integer createdAt;
    private String text;

    @ManyToOne
    @JoinColumn(name = "author_id")
    private UserEntity author;

    @ManyToOne
    @JoinColumn(name = "ad_id")
    private AdEntity ad;
}
