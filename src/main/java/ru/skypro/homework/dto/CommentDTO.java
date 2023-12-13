package ru.skypro.homework.dto;

import lombok.Data;

@Data
public class CommentDTO {
    private Integer author;
    private Integer pk;
    private String authorImage;
    private String authorFirstName;
    private Long createdAt;
    private String text;
}
