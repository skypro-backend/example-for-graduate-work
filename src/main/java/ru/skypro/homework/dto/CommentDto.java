package ru.skypro.homework.dto;

import lombok.Data;

/**
 * CommentDto is the Data Transfer Object used to create and update a comment
 * @author radyushinaalena and AlexBoko
 */
@Data
public class CommentDto {
    private int author;
    private String authorImage;
    private String authorFirstName;
    private long createdAt;
    private int pk;
    private String text;
}