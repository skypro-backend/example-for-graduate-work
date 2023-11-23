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
    private int author;
    private String authorImage;
    private String authorFirstName;
    private int createdAt;
    private String text;

}