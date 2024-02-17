package ru.skypro.homework.dto;

import lombok.Data;

import java.util.Date;

@Data
public class CommentDTO {
    private Integer author;
    private String authorImage;
    private String authorFirstName;
    private Date createdAt;
    private Integer pk;
    private String text;
}
