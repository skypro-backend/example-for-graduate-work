package ru.skypro.homework.models;

import lombok.Data;

@Data
public class Comment {
    private Long authorId;
    private String authorImage;
    private String authorFirstName;
    private Long createdAt;
    private Integer pkId;
    private String text;
}
