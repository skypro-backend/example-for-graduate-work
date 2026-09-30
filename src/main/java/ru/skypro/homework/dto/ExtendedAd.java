package ru.skypro.homework.dto;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;

@Data
@Schema(description = "Detailed advertisement information")
public class ExtendedAd {

    @Schema(description = "Advertisement primary key")
    private Integer pk;

    @Schema(description = "Author first name")
    private String authorFirstName;

    @Schema(description = "Author last name")
    private String authorLastName;

    @Schema(description = "Advertisement description")
    private String description;

    @Schema(description = "Author email")
    private String email;

    @Schema(description = "Image URL")
    private String image;

    @Schema(description = "Author phone number")
    private String phone;

    @Schema(description = "Advertisement price")
    private Integer price;

    @Schema(description = "Advertisement title")
    private String title;
}