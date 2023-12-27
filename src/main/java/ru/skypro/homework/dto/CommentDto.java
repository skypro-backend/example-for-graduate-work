package ru.skypro.homework.dto;

import lombok.Data;
import ru.skypro.homework.model.Comment;

@Data
public class CommentDto {
    private Integer author;
    private String authorImage;
    private String authorFirstName;
    private Long createdAt;
    private Integer pk;
    private String text;

    public CommentDto(Comment comment) {
        this.author = comment.getAuthor();
        this.authorImage = comment.getAuthorImage();
        this.authorFirstName = comment.getAuthorFirstName();
        this.createdAt = comment.getCreatedAt();
        this.pk = comment.getPk();
        this.text = comment.getText();
    }

    public CommentDto(Integer author, String authorImage, String authorFirstName, Long createdAt, Integer pk, String text) {
        this.author = author;
        this.authorImage = authorImage;
        this.authorFirstName = authorFirstName;
        this.createdAt = createdAt;
        this.pk = pk;
        this.text = text;
    }
}
