package ru.skypro.homework.data.dto.ads;

import lombok.Data;

import java.util.List;

@Data
public class AdsResponse {
    private Integer count;
    private List<AdItemDto> results;
}
