package ru.skypro.homework.dto.ad;

import lombok.Data;

@Data
public class AdDto {
    private Integer pk;
    private String image;
    private Integer author;
    private String title;
    private Integer price;
}
