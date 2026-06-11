package ru.skypro.homework.dto.Ads;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Getter;
import lombok.Setter;

import java.util.ArrayList;
import java.util.List;

@Getter
@Setter
@Schema(name = "Ads")
public class AdsDto {

    @Schema(description = "общее количество объявлений")
    private Integer count;

    @Schema(description = "список объявлений")
    private List<AdDto> results = new ArrayList<>();

    public AdsDto() {
        this.count = 0;
        this.results = new ArrayList<>();
    }
}
