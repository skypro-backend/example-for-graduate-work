package ru.skypro.homework.dto;

import lombok.Data;

@Data
public class Comment {
    Integer author;
    String authorImage;
    String authorFirstName;
    Long createdAt;
    Integer pk;
    String text;
}
