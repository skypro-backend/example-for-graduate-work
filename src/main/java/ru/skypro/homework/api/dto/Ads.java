package ru.skypro.homework.api.dto;

import io.swagger.v3.oas.annotations.media.ArraySchema;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;

import java.util.ArrayList;
import java.util.List;

@Data
@Schema(description = "Список объявлений")
public class Ads {

    @Schema(description = "общее количество объявлений")
    private Integer count = 0;

    @ArraySchema(schema = @Schema(implementation = Ad.class))
    private List<Ad> results = new ArrayList<>();
}
