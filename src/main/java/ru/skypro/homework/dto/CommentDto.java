package ru.skypro.homework.dto;

import lombok.Data;
import lombok.RequiredArgsConstructor;

@Data
@RequiredArgsConstructor
public class CommentDto {
    int author;
    String authorImage;
    String authorFirstName;
    int createdAt;
    int pk;
    String text;

}
