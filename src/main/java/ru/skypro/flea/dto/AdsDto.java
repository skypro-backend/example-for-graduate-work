package ru.skypro.flea.dto;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;

import java.util.List;

@Data
public class AdsDto {

    @Schema(description = "Total amount of ads")
    private Integer count;

    private List<AdDto> results;

}
