package ru.skypro.homework.dto;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.NotBlank;
import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder


public class AdsDto {

    @Schema(description = "общее количество объявлений")
    @NotBlank
    private Integer count;

    @Schema(description = "#/components/schemas/Ad")
    @NotBlank
    private List<AdDto> results;

    public AdsDto(List<AdDto> results) {
        this.results = results;
        this.count = results.size();
    }
}
