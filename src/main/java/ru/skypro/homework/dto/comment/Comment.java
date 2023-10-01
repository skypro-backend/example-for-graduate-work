package ru.skypro.homework.dto.comment;

import lombok.Data;

@Data
public class Comment {
    private int author;
    private String image;
    private int pk;
    private int price;
    private String title;
}
