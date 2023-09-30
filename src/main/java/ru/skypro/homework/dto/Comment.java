package ru.skypro.homework.dto;

import lombok.Data;

@Data
public class Comment {
    Integer authorId;
    String linkToImage;
    String authorName;
    Integer DateAndTimeCommentCreate;
    int commentId;
    String commentText;
}
