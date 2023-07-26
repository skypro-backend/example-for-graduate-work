package ru.skypro.flea.dto;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;

@Data
public class ExtendedAdDto {

    @Schema(description = "Ad's id")
    private Integer pk;

    @Schema(description = "Ad's author first name")
    private String authorFirstName;

    @Schema(description = "Ad's author last name")
    private String authorLastName;

    @Schema(description = "Ad's description")
    private String description;

    @Schema(description = "Ad's author login (e-mail)")
    private String email;

    @Schema(description = "Ad's image link")
    private String image;

    @Schema(description = "Ad's author phone")
    private String phone;

    @Schema(description = "Ad's price")
    private Integer price;

    @Schema(description = "Ad's title")
    private String title;

}
