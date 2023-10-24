package ru.skypro.homework.dto;

import lombok.Data;

import java.util.List;

@Data
public class AdsDto {
    private Integer count;
    private List<AdDto> results;

    public AdsDto(List<AdDto> results) {
        this.results = results;
        this.count = results.size();
    }
}
