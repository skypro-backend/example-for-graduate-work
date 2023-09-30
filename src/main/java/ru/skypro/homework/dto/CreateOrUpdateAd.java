package ru.skypro.homework.dto;

import lombok.Data;

@Data
public class CreateOrUpdateAd {
    String adTitle;
    int adPrice;
    String descriptionOfTheAdd;
}
