package ru.skypro.homework.dto;

import lombok.Data;

import java.util.Date;

@Data
public class Comment { // оставить
    private Integer author;
    private Integer id;
    private String text;
    private Date createdAt;
}
