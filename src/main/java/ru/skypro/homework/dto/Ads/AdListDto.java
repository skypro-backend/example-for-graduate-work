package ru.skypro.homework.dto.Ads;

import java.util.ArrayList;
import java.util.List;

public class AdListDto {
    private Long count;
    private List<AdShortDto> results = new ArrayList<>();

    // Конструктор по умолчанию (нужен для скелета)
    public AdListDto() {
        this.count = 0L;
        this.results = new ArrayList<>();
    }


}
