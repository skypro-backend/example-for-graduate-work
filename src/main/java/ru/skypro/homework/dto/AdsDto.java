package ru.skypro.homework.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;

import java.util.List;

@Data

@AllArgsConstructor
@Builder
public class AdsDto {
    private Integer count;
    private List<AdDto> results;
}
