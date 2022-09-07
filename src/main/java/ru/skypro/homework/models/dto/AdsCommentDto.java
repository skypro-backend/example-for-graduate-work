package ru.skypro.homework.models.dto;

import lombok.Data;

@Data
public class AdsCommentDto {
    private Integer author;
    private String createdAt;
    private Integer pk;
    private String text;
}
