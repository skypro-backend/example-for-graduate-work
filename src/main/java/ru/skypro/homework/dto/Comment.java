package ru.skypro.homework.dto;

import lombok.*;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class Comment {
    private int author;
    private String authorImage;
    private String authorFirstName;
    private long createdAt;
    private int pk;
    private String text;
}