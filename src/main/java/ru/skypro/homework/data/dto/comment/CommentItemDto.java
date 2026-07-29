package ru.skypro.homework.data.dto.comment;

import lombok.Data;

@Data
public class CommentItemDto {
    private Integer author;
    private String authorImage;
    private String authorFirstName;
    private Long createdAt;
    private Integer pk;
    private String text;
}
