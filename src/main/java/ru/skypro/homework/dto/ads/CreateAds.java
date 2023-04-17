package ru.skypro.homework.dto.ads;

import lombok.Data;

@Data
public class CreateAds {
    private String description;
    private Integer price;
    private String title;
}
