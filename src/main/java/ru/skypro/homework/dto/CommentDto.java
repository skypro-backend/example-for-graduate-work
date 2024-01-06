package ru.skypro.homework.dto;

import lombok.Data;
import ru.skypro.homework.model.Comment;

@Data
public class CommentDto {
    private Integer author;
    private String authorImage;
    private String authorFirstName;
    private String createdAt;
    private Integer pk;
    private String text;

 }
