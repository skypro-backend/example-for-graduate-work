package ru.skypro.homework.dto;

import javax.validation.constraints.NotEmpty;
import java.util.List;

public record AdsDto(
         //общее количество объявлений
        Integer count,
        @NotEmpty
        List <AdDto> results
) {
}
