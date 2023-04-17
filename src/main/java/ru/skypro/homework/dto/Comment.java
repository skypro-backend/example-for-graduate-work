package ru.skypro.homework.dto;

import lombok.Data;

import java.time.Instant;

@Data
public class Comment {
    private Long author;
    private String authorImage;
    private String authorFirstName;
    private Instant createdAt;
    private Long pk;
    private String text;
}
