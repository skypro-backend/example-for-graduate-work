package ru.skypro.homework.dto;

import lombok.Data;

@Data
public class CommentDto {

    private Long author;
    private String authorImage;
    private String authorFirstName;
    private Long createdAt;
    private Long pk;
    private String text;

}
