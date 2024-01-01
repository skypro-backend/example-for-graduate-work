package ru.skypro.homework.dto.Listing;

import lombok.Data;

@Data
public class Listing {
    private Integer author;
    private String image;
    private Integer pk;
    private Integer price;
    private String title;
}
