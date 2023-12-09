package ru.skypro.homework.dto;

import lombok.Data;
import java.time.LocalDateTime;

@Data
public class Comment {
    private Integer id;
    private String text;
    private LocalDateTime createdAt;
    private Integer author;
    private String authorImage;
    private String authorFirstName;
}
