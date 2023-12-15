package ru.skypro.homework.dto;

/**
 * DTO for extended advertisement
 */

import lombok.Data;

@Data
public class ExtendedAd {
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
