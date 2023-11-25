package ru.skypro.homework.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@AllArgsConstructor
@NoArgsConstructor
@Data
public class CommentDTO {
    private int author;
    private String authorImage;
    private String authorFirstName;
    private LocalDateTime createdAt;
    private int pk;
    private String text;
}