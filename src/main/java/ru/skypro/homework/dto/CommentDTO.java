package ru.skypro.homework.dto;

import lombok.Data;

@Data
public class CommentDTO {
    private Long author;

    private String authorImage;

    private String authorFirstName;

    private Integer createdAt;

    private Long pk;

    private String text;


}
