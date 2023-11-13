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
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer pk;

    @NonNull
    private String text;
    @NonNull
    private Long createdAt;

    @Getter
    @ManyToOne
    @JoinColumn(name = "author")
    private User author;

    @Getter
    @ManyToOne
    @JoinColumn(name = "ad")
    private Ad ad;

    public Comment() {

    }

    public void setAuthor(final User author) {
        this.author = author;
    }

    public void setAd(final Ad ad) {
        this.ad = ad;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Comment comment = (Comment) o;
        return Objects.equals(pk, comment.pk) && Objects.equals(author, comment.author) && Objects.equals(text, comment.text) && Objects.equals(createdAt, comment.createdAt) && Objects.equals(ad, comment.ad);
    }

    @Override
    public int hashCode() {
        return Objects.hash(pk, author, text, createdAt, ad);
    }

}
