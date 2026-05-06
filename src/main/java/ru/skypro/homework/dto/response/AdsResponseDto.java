package ru.skypro.homework.dto.response;

import lombok.Value;

import java.util.List;

@Value
public class AdsResponseDto {
    Integer count;
    List<AdResponseDto> results;
}
