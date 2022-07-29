package ru.skypro.homework.dto;

import lombok.Data;

import java.time.LocalDateTime;

@Data
public class AdsComment {
    private int author;
    private LocalDateTime createdAt;
    private int pk;
    private String text;
}
