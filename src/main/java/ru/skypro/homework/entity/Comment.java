package ru.skypro.homework.entity;

//import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.time.LocalDateTime;
import java.util.Objects;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "comments")
public class Comment {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "comment_id",nullable = false)
    private Integer pk;
//    Марина: добавила private

    @ManyToOne  (fetch = FetchType.EAGER)
    @JoinColumn (name = "author_id")
    private Users users;

    @ManyToOne  (fetch = FetchType.EAGER)
    @JoinColumn (name = "ad_id")
    private Ad ad;

    @Column(name = "created_at", nullable = false)
//            Марина:изменила имя для бд
    LocalDateTime createdAt;

    @Column(name = "text", nullable = false)
//    @Size(min=8, max=64)
    private String text;

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Comment comment = (Comment) o;
        return Objects.equals(pk, comment.pk) && Objects.equals(createdAt, comment.createdAt) && Objects.equals(text, comment.text);
    }

    @Override
    public int hashCode() {
        return Objects.hash(pk, createdAt, text);
    }


}
