package ru.skypro.homework.dto;

import java.util.List;

public record AdsDto(
        /**
         общее количество объявлений
         */
        Integer count,
        List <AdDto> results
) {
}
