package ru.skypro.homework.dto.Listing;

import lombok.Data;

@Data
public class ExtendedListingDTO {
    private Integer pk;
    private String authorFirstName;
    private String authorLastName;
    private String description;
    private String email;
    private String image;
    private String phone;
    private Integer price;
    private String title;

}


