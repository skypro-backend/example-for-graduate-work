package ru.skypro.homework.dto;

import lombok.Data;

@Data
public class AdDto {
    private Long author;
    private String image;
    private Integer pk;
    private Integer price;
    private String title;

    public AdDto(Long author, String image, Integer pk, Integer price, String title) {
        this.author = author;
        this.image = image;
        this.pk = pk;
        this.price = price;
        this.title = title;
    }

}
