package ru.skypro.homework.dto;

import lombok.Data;

@Data
public class CommentDTO {

    private Long id;
    private String authorImage;
    private String firstName;
    private int createdAt;
    private Long commentId;
    private String text;

}
