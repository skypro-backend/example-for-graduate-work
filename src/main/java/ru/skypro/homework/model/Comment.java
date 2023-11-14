package ru.skypro.homework.model;

import jakarta.persistence.*;
import lombok.Data;
import ru.skypro.homework.dto.CommentDTO;


import java.time.LocalDateTime;

@Entity
@Data
public class Comment {

    @Id
    @GeneratedValue(strategy= GenerationType.IDENTITY)
    @Column(name = "comment_id")
    private long id;

    private String createdAt = String.valueOf(LocalDateTime.now());
    private String text;

    @ManyToOne(fetch = FetchType.LAZY, cascade = {
            CascadeType.DETACH,
            CascadeType.MERGE,
            CascadeType.PERSIST,
            CascadeType.REFRESH
    })
    UserInfo author;

    @ManyToOne(fetch = FetchType.LAZY, cascade = {
            CascadeType.DETACH,
            CascadeType.MERGE,
            CascadeType.PERSIST,
            CascadeType.REFRESH
    })
    @JoinColumn(name = "ads_id", referencedColumnName = "ads_id")
    Ads ads;

    public static CommentDTO mapToCommentDto(Comment comment) {
        return new CommentDTO(comment.getAuthor().getId(),
                comment.getAuthorImage(comment),
                comment.getAuthor().getFirstName(),
                comment.getCreatedAt(),
                comment.getId(),
                comment.getText());
    }

    private String getAuthorImage(Comment comment) {
        return null;
    }
}
