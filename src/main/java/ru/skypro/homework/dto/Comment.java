package ru.skypro.homework.dto;

import lombok.Data;

@Data
public class Comment { // оставить
    private Integer author;
    private Integer id;
    private String text;
    private String createdAt;
}
