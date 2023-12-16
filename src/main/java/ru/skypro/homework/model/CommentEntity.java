package ru.skypro.homework.model;

import lombok.Data;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

@Data
@Entity
@Table(name = "comments")
public class CommentEntity {
    @Id
    private int id;
    private long createdAt;
    private String text;
    @ManyToOne
    private UserEntity author;
    @ManyToOne
    private AdEntity ad;
}
