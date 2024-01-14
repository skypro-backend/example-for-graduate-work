package ru.skypro.homework.dto;

import lombok.Data;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;

@Data
public class CreateOrUpdateAd {
    @NotBlank
    @Size(min = 8)
    private String title;
    private int price;
    @NotBlank
    @Size(min = 8)
    private String description;
}
