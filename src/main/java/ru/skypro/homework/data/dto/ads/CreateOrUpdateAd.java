package ru.skypro.homework.data.dto.ads;

import lombok.Data;

import java.math.BigDecimal;

@Data
public class CreateOrUpdateAd {
    private String title;
    private String description;
    private Integer price;
}
