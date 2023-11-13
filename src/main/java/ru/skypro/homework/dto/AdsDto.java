package ru.skypro.homework.dto;

import lombok.Data;

import java.util.List;

@Data
public class AdsDto {

    private Integer count;
    private List<AdDto> results;

}
