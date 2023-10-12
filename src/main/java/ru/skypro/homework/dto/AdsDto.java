package ru.skypro.homework.dto;

import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@NoArgsConstructor
public class AdsDto {
    private int count;
    private List<AdDto> results;

    public AdsDto(List<AdDto> list) {
        count = list == null ? 0 : list.size();
        results = list;
    }
}