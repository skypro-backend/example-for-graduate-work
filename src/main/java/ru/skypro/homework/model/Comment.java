package ru.skypro.homework.model;

import lombok.Data;

import javax.persistence.*;
import javax.validation.constraints.Size;

@Entity
@Table(name = "comments")
@Data
public class Comment {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private long pk;

    @Column(name = "user_id")
    long userId;

    @Column(name = "created_at")
    private Integer createdAt;

    @Column(name = "text")
    @Size(min = 8, max = 64)
    private String text;


    public Comment(Integer pk, long userId, Integer createdAt, String text) {
        this.pk = pk;
        this.userId = userId;
        this.createdAt = createdAt;
        this.text = text;
    }
}
