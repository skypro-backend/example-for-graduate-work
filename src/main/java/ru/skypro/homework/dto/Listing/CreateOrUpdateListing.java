package ru.skypro.homework.dto.Listing;

import lombok.Data;

@Data
public class CreateOrUpdateListing {
    private String title;
    private Integer price;
    private String description;
}
