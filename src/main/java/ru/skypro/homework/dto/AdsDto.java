package ru.skypro.homework.dto;

import lombok.AllArgsConstructor;
import lombok.Data;

import java.util.List;

@Data

@AllArgsConstructor
public class AdsDto {
    private Integer count;
    private List<AdDto> result;
}
