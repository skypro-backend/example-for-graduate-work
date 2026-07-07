package ru.skypro.homework.dto;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class Ads {

    @Schema(description = "Общее количество объявлений", example = "15")
    private Integer count;

    @Schema(description = "Список объявлений пользователя",
            example = "[{\"pk\": 101, \"title\": \"Диван угловой\", \"price\": 15000, \"image\": \"https://example.com/img1.jpg\", \"author\": 55}]")
    private List<Ad> results;
}
