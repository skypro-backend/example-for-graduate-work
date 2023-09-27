package ru.skypro.homework.dto.comment;

import lombok.Data;

@Data
public class CommentDto {
    private Integer pk;
    private Integer author;
    private String authorImage;
    private String authorFirstName;
    private String text;
    private Long createdAt;
}
