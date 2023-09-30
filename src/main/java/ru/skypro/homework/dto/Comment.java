package ru.skypro.homework.dto;

import lombok.Data;

@Data
public class Comment {
    int author;
    String authorImage;
    String authorName;
    Long createdAt;
    int id;
    String text;
}
