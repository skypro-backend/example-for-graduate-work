package ru.skypro.homework.dto;

import lombok.Data;

import java.math.BigDecimal;

@Data
public class CreateOrUpdateAddDto {
    private String title;
    private BigDecimal price;
    private String description;
}
