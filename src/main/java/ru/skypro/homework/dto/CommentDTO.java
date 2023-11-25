package ru.skypro.homework.dto;

import lombok.Data;

import java.time.LocalDateTime;

@Data
public class CommentDTO {
    private long id;
    private String authorFirstName;
    private LocalDateTime createdAt;
    private String text;
    private long adId;
    private long userId;
    private long userAvatarId;
}
