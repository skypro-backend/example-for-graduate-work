package ru.skypro.homework.dto;

import lombok.Data;

import java.util.List;

@Data
public class AdsDto {
    Integer count;
    List<AdDto> results;

    public AdsDto(List<AdDto> allAdsUser) {
    }
}
