package ru.skypro.homework.dto;

import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

/**
 * AdsDto is the Data Transfer Object used to receive all ads and all ads of an authorized user
 * @author radyushinaalena + AlexBoko
 */
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