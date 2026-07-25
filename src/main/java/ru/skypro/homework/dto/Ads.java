package ru.skypro.homework.dto;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.ArrayList;
import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Schema(description = "Список объявлений")
public class Ads {

    @Schema(description = "Количество объявлений", example = "1")
    private Integer count = 0;

    @Schema(description = "Список объявлений")
    private List<Ad> results = new ArrayList<>();
}
