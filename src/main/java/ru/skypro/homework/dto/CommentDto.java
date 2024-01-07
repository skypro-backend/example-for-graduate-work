package ru.skypro.homework.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import ru.skypro.homework.model.Comment;
@AllArgsConstructor
@NoArgsConstructor
@Data
public class CommentDto {
    private Integer author;
    private String authorImage;
    private String authorFirstName;
    private String createdAt;
    private Integer pk;
    private String text;

 }
