package ru.skypro.homework.dto;

import lombok.Data;

@Data
public class Comment {
    private Integer pk;
    private String text;
    private Long createdAt;
    private Integer author;
    private String authorImage;
    private String authorFirstName;
}
