package ru.skypro.homework.dto.listing;

import lombok.Data;

@Data
public class CreateOrUpdateListing {
    private String title;
    private Integer price;
    private String description;
}
