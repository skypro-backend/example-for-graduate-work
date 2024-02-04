package ru.skypro.homework.dto;


import lombok.Data;

import java.util.List;

@Data
public class AdsDTO {
    private int count;
    private List<AdDTO> results;
    public static AdsDTO of(List<AdDTO> results) {
        AdsDTO responseWrapper = new AdsDTO();
        if (results == null) {
            return responseWrapper;
        }
        responseWrapper.results = results;
        responseWrapper.count = results.size();
        return responseWrapper;
    }
}
