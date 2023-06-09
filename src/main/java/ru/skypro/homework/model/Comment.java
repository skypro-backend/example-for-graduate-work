package ru.skypro.homework.model;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.Entity;
import javax.persistence.*;
import java.util.Objects;

@Getter
@Setter
@Entity
@Table (name = "comments")
public class Comment {
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE)
    @Column(name = "id", nullable = false)
    private int id;

    private String authorFirstName;
    private String authorImage;
    private int createdAt;
    private int pk;
    private String text;

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Comment comment = (Comment) o;
        return createdAt == comment.createdAt && pk == comment.pk && Objects.equals(id, comment.id) && Objects.equals(authorFirstName, comment.authorFirstName) && Objects.equals(authorImage, comment.authorImage);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, authorFirstName, authorImage, createdAt, pk);
    }
}
