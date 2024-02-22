package ru.skypro.homework.dto.comment;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Getter
@Data
@NoArgsConstructor
@AllArgsConstructor
public class CommentDTO {
    private long author;
    private String authorImage;
    private String authorFirstName;
    private LocalDateTime createdAt;
    private long pk;
    private String text;

    public void setAuthor(long author) {
        this.author = author;
    }

    public void setAuthorImage(String authorImage) {
        this.authorImage = authorImage;
    }

    public void setAuthorFirstName(String authorFirstName) {
        this.authorFirstName = authorFirstName;
    }

    public void setCreatedAt(LocalDateTime createdAt) {
        this.createdAt = createdAt;
    }

    public void setPk(long pk) {
        this.pk = pk;
    }

    public void setText(String text) {
        this.text = text;
    }
}
