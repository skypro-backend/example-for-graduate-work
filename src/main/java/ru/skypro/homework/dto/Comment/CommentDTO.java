package ru.skypro.homework.dto.Comment;

import lombok.Data;

@Data
public class CommentDTO {
    private Integer author;
    private String authorImage;
    private String authorFirstName;
    private Integer createdAt;
    private Integer pk;
    private String text;
}
