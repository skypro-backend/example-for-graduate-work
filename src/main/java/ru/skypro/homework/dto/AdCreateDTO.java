package ru.skypro.homework.dto;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;

@Data
public class AdCreateDTO {

    @Schema(hidden = true)
    private Long author;
    @Schema(hidden = true)
    private String image;
    @Schema(hidden = true)
    private Long pk;
    private Long price;
    private String title;
    private String description;
}
