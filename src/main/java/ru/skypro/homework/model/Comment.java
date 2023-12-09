package ru.skypro.homework.model;

import lombok.Data;

import javax.persistence.*;

@Entity
@Table(name = "comments")
@Data
public class Comment {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int pk;
    private long createdAt;
    private String text;

    @ManyToOne
    private Ad ad;
    @ManyToOne
    private User user;
}
