package ru.skypro.flea.dto;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;

@Data
public class AdDto {

    @Schema(description = "Ad's author id")
    private Integer author;

    @Schema(description = "Ad's image link")
    private String image;

    @Schema(description = "Ad's id")
    private Integer pk;

    @Schema(description = "Ad's price")
    private Integer price;

    @Schema(description = "Ad's title")
    private String title;

}
