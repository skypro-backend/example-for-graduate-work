package ru.skypro.homework.dto;

import lombok.Data;

@Data
public class CommentDTO {
    private Long id;
    private Long author;
    private String authorImage;
    private String authorFirstName;
    private Long createdAt;
    private String text;
}
