package ru.skypro.homework.dto;

import lombok.*;

import java.util.List;
@Data
@AllArgsConstructor
public class AdsDto {
    private int count;
    private List<AdDto> results;
}
