package ru.skypro.flea.dto;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;

import javax.validation.constraints.Max;
import javax.validation.constraints.Min;
import javax.validation.constraints.Size;

@Data
public class CreateOrUpdateAdDto {

    @Schema(description = "Ad's title")
    @Size(min = 4, max = 32)
    private String title;

    @Schema(description = "Ad's price")
    @Min(0)
    @Max((long) 1e7)
    private Integer price;

    @Schema(description = "Ad's description")
    @Size(min = 8, max = 64)
    private String description;

}
