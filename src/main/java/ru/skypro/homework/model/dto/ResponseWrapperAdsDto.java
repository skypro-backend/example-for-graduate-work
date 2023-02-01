package ru.skypro.homework.model.dto;

import lombok.Data;

import java.util.List;

@Data
public class ResponseWrapperAdsDto {
    private Integer count;

    private List<AdsDto> results;
}
