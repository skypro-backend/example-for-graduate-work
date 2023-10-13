package ru.skypro.homework.dto;

import lombok.Data;

@Data
public class Comment {
    int author;
    String authorImage;
    String authorFirstName;
    int createdAt;
    int pk;
    String text;
}
