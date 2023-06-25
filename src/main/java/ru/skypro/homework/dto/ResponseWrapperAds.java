package ru.skypro.homework.dto;

import lombok.Data;

@Data
public class ResponseWrapperAds {
    private int count;
    private Ads[] array;
}
