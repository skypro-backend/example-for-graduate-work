package ru.skypro.homework.dto.comment;

import lombok.Data;

@Data
public class Comment {
    private int author;
    private String authorFirstName;
    private int createdAt;
    private int pk;
    private String text;

}
