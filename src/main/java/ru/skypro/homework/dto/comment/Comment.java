package ru.skypro.homework.dto.comment;

import lombok.Data;

@Data
public class Comment {
    private int author;
    private String Image;
    private String authorFirstName;
    private Long createdAt;
    private int pk;
    private String text;

}
