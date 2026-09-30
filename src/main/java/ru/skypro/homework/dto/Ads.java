package ru.skypro.homework.dto;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;

import java.util.List;

@Data
@Schema(description = "List of advertisements")
public class Ads {

    @Schema(description = "Number of advertisements")
    private Integer count;

    @Schema(description = "Advertisements")
    private List<Ad> results;
}