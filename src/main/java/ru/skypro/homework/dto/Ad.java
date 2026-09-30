package ru.skypro.homework.dto;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;

@Data
@Schema(description = "Advertisement")
public class Ad {

    @Schema(description = "Author ID")
    private Integer author;

    @Schema(description = "Image URL")
    private String image;

    @Schema(description = "Advertisement primary key")
    private Integer pk;

    @Schema(description = "Advertisement price")
    private Integer price;

    @Schema(description = "Advertisement title")
    private String title;
}