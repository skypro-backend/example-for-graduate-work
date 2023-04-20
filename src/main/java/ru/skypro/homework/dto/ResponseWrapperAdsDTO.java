package ru.skypro.homework.dto;

import lombok.Data;

import java.util.List;

@Data
public class ResponseWrapperAdsDTO {
    // Общее количество объявлений
    private Integer count;
    private List<AdsDTO> results;
}
