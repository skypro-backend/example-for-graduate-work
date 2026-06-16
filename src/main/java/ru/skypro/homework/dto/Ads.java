package ru.skypro.homework.dto;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Schema(description = "информация о количестве объявлений")
public class Ads {
    @Schema(description = "общее количество объявлений", example = "23")
    private Integer count;
    @Schema(description = "найденные объявления")
    private List<Ad> results;
}
