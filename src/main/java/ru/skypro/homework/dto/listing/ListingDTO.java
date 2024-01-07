package ru.skypro.homework.dto.listing;

import lombok.Data;

@Data
public class ListingDTO {
    private Integer author;
    private String image;
    private Integer pk;
    private Integer price;
    private String title;
}
