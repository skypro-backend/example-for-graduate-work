package ru.skypro.homework.dto;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;
import java.util.List;

@Data
@Schema(description = "общее количество объявлений и список объявлений")
public class Ads {

    @Schema(description = "общее количество объявлений")
    private Long count;

    @Schema(description = "список объявлений")
    private List<Ad> results;
}
