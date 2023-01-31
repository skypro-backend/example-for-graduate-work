package ru.skypro.homework.dto;

import lombok.Data;
import ru.skypro.homework.model.Ads;

import java.util.List;

@Data
public class ResponseWrapperAdsDto {
    private Integer count;

    private List<Ads> results;
}
