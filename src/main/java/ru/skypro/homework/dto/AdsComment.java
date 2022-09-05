package ru.skypro.homework.dto;

import lombok.Data;

@Data
public class AdsComment {
    private Integer author;
    private String createdAt;
    private Integer pk;
    private String text;
}
