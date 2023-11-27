package ru.skypro.homework.dto;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;

import javax.validation.constraints.NotBlank;
import java.lang.reflect.Array;
@Data
public class Ads {

    @Schema(description = "общее количество объявлений")
    @NotBlank
    private Integer count;

    @Schema(description = "#/components/schemas/Ad")
    @NotBlank
    private Array results;

}