package ru.skypro.homework.dto.comments;

import lombok.Data;

@Data
public class CommentDto {

    private Integer author;
    private String authorImage;
    private String authorFirstName;
    private Long createdAt;
    private Integer pk;
    private String text;
}
