package ru.skypro.homework.model;

import lombok.Data;

import javax.persistence.*;

@Data
@Entity
@Table(name = "comments")
public class CommentEntity {
    @Id
    private int id;
    private long createdAt;
    private String text;
    @JoinColumn(name = "author_id")
    @ManyToOne
    private UserEntity author;
    @JoinColumn(name = "ad_id")
    @ManyToOne
    private AdEntity ad;
}
