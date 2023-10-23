package ru.skypro.homework.dto;

import lombok.Data;

import java.math.BigDecimal;

@Data
public class AdDto {
    private Long author;
    private String image;
    private Long pk;
    private BigDecimal price;
    private String title;
}
