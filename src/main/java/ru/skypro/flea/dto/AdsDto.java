package ru.skypro.flea.dto;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;

@Data
public class AdsDto {

    @Schema(description = "Total amount of ads")
    private Integer count;

    private AdDto[] results;

}
