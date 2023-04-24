package ru.skypro.homework.model;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.time.Instant;

@Entity
@Table(name = "comments")
@Getter
@Setter
public class CommentModel {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;
    @Column(name = "comment_text")
    private String text;
    @ManyToOne
    private UserModel user;
    @ManyToOne
    private AdsModel ads;
    private Instant createdAt;

}
