package ru.skypro.homework.dto;

import lombok.Data;
import lombok.Getter;

@Data
public class AdDto {
    @Getter
    private int author;
    private String image;
    private int pk;
    private int price;
    private String title;

    public AdDto() {
        this.image = image;
        this.title = title;
    }

}