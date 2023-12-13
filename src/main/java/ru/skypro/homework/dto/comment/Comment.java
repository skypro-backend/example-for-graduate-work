package ru.skypro.homework.dto.comment;

import lombok.Data;

@Data
public class Comment {
    private Integer author;
    private String authorImage;
    private String authorFirstName;
    private Long createdAt;
    private int pk;
    private String text;

}
