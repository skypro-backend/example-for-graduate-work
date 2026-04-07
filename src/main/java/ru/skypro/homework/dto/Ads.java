package ru.skypro.homework.dto;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;
import lombok.Builder;
import lombok.NoArgsConstructor;
import lombok.AllArgsConstructor;
import java.util.List;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Schema(description = "Контейнер для списка объявлений")
public class Ads {

    @Schema(description = "общее количество объявлений", example = "5")
    private Integer count;

    @Schema(description = "список объявлений")
    private List<Ad> results; // Нужен класс Ad
}
