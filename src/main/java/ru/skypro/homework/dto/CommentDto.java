package ru.skypro.homework.dto;

import lombok.Data;
import lombok.Getter;

@Data
public class CommentDto {
    @Getter
    private int author;
    private String authorImage;
    private String authorFirstName;
    private long createdAt;
    private int pk;
    private String text;

    public CommentDto(){
        this.authorImage = authorImage;
        this.authorFirstName = authorFirstName;
        this.text = text;
    }
}
