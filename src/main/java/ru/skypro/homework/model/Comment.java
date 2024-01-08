package ru.skypro.homework.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import javax.validation.constraints.Size;

/**
 * <h2>Comment</h2>
 * Represents entity of comment published by user to advertisement or other comment
 */
@Entity
@Table(name = "comments")
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Comment {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private long pk;

    @Column(name = "user_id")
    long userId;

    @Column(name = "created_at")
    private long createdAt;

    @Column(name = "text")
    @Size(min = 8, max = 64)
    private String text;


    /*public Comment(Integer pk, long userId, Integer createdAt, String text) {
        this.pk = pk;
        this.userId = userId;
        this.createdAt = createdAt;
        this.text = text;
    }*/
}
