package ru.skypro.homework.dto;

import lombok.Data;

import java.util.ArrayList;

@Data
public class ResponseWrapperAds {
    private Integer count;
    private ArrayList<Ads> results;
}
