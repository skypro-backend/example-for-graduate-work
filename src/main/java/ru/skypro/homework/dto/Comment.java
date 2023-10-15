package ru.skypro.homework.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
@AllArgsConstructor
@Data
public class Comment {
    private int author;
    private String authorImage;
    private  String authorFirstName;
    private  int createdAt;
    private  int pk;
    private   String text;
}
