package ru.skypro.homework.dto;

import lombok.Data;

import java.util.Objects;

@Data
public class CreateOrUpdateAd {
    private String title;
    private Integer price;
    private String description;

}
