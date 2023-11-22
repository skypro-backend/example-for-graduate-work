package ru.skypro.homework.dto;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.*;

import java.util.List;

@Setter
@Getter
@ToString
@AllArgsConstructor
@RequiredArgsConstructor
@Schema(description = "список объявлений")
public class AdsDto {

    @Schema(description = "количество объявлений")
    private int count;

    @Schema(description = "объявления")
//    private List<Ad> results;
    private List<AdDto> results;

}
