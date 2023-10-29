package ru.skypro.homework.entity;

import javax.persistence.*;
import lombok.*;

import java.util.Objects;

@Entity
@Data
@RequiredArgsConstructor
@ToString
@Table(name = "comments")
public class Comment {

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE)
    private int pk;

    @ManyToOne
    @JoinColumn(name = "author")
    private User author;
    @NonNull
    private String text;
    @NonNull
    private Long createdAt;

    @ManyToOne
    @JoinColumn(name = "ad")
    private Ad ad;

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Comment comment = (Comment) o;
        return pk == comment.pk && Objects.equals(author, comment.author) && Objects.equals(text, comment.text) && Objects.equals(createdAt, comment.createdAt) && Objects.equals(ad, comment.ad);
    }

    @Override
    public int hashCode() {
        return Objects.hash(pk, author, text, createdAt, ad);
    }

}
