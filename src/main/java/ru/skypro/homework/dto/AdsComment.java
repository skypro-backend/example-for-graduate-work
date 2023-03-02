package ru.skypro.homework.dto;

import lombok.Data;

@Data
public class AdsComment {
    private Integer author;
    private Integer id; // pk
    private String text;
    private String createdAt;
}
