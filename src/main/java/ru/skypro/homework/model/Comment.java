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
    @OneToOne
    @JoinColumn(name = "user_id")
    User user;
    @Column(name = "created_at")
    private Integer createdAt;
    @Column(name = "text")
    @Size(min = 8, max = 64)
    private String text;

    public Comment(Integer pk, User user, Integer createdAt, String text) {
        this.pk = pk;
        this.user = user;
        this.createdAt = createdAt;
        this.text = text;
    }
}
