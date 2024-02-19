package ru.skypro.homework.dto;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class CommentDTO {
    private Long pk;
    private Long author;
    private String authorImage;
    private String authorFirstName;
    private Long createdAt;
    private String text;
}
