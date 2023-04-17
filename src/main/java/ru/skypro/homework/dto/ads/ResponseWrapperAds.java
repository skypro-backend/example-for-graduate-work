package ru.skypro.homework.dto.ads;

import lombok.Data;

import java.util.List;

@Data
public class ResponseWrapperAds {
    private Long count;
    private List<Ads> results;
}
