package ru.skypro.homework.model;

import lombok.Data;

@Data
public class CommentModel {
    private int author;
    private String authorImage;
    private String authorFirstName;
    private long createdAt;
    private int pk;
    private String text;

    public CommentModel(int author, String authorImage, String authorFirstName, long createdAt, int pk, String text){
        this.author = author;
        this.authorImage = authorImage;
        this.authorFirstName = authorFirstName;
        this.createdAt = createdAt;
        this.pk = pk;
        this.text = text;
    }
}
