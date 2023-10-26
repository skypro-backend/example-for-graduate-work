package ru.skypro.homework.dto;

import lombok.Data;

@Data
public class CommentDTO {
    private long authorId;
    private String authorImage;
    private String authorFirstName;
    private long createdAt;
    private long pk;
    private String text;

}
