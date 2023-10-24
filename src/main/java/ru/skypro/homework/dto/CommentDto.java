package ru.skypro.homework.dto;

import lombok.Data;

@Data
public class CommentDto {

    private Integer pk;
    private Integer author;
    private String authorFirstName;
    private String authorImage;
    private String text;
    private Long createdAt;

}
