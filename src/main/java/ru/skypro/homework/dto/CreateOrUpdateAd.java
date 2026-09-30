package ru.skypro.homework.dto;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;

@Data
@Schema(description = "Data for creating or updating an ad")
public class CreateOrUpdateAd {

    @Schema(description = "Ad title", minLength = 4, maxLength = 32)
    private String title;

    @Schema(description = "Ad price", minimum = "0", maximum = "10000000")
    private Integer price;

    @Schema(description = "Ad description", minLength = 8, maxLength = 64)
    private String description;
}