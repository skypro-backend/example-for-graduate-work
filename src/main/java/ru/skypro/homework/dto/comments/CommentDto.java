package ru.skypro.homework.dto.comments;

import lombok.Data;

@Data
public class CommentDto {
    private Integer pk;
    private Integer author;
    private String authorImage;
    private String authorFirstName;
    private String text;
    private Integer createdAt;

}
