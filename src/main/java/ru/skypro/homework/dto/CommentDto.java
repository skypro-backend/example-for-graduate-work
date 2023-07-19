package ru.skypro.homework.dto;

import lombok.Data;

@Data
public class CommentDto {
    Integer author;
    String authorImage;
    String authorFirstName;
    Long createdAt;
    Integer pk;
    String text;

    public CommentDto(Integer userId, String image, String firstName, Long createdTime, Integer comments, String text) {
    }
}
