package ru.skypro.homework.dto;

import lombok.Data;

@Data
public class ResponseWrapperAdsDto {
    private int count;
    private AdsDto[] results;
}
