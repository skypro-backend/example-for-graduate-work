package ru.skypro.homework.dto;

import lombok.Data;

import java.util.List;
@Data
public class ResponseWrapperAdsDTO {
    private int count;
    private List<AdsDTO> results;
}
