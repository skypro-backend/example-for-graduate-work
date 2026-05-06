package ru.skypro.homework.dto.response;

import lombok.Value;

@Value
public class CommentResponseDto {
    Integer author;
    String authorImage;
    String authorFirstName;
    Long createdAt;
    Integer pk;
    String text;
}
