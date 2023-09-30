package ru.skypro.homework.dto;

import lombok.Data;

import javax.validation.constraints.Max;
import javax.validation.constraints.Min;
import javax.validation.constraints.Size;

@Data
public class CreateOrUpdateAd {
    @Size(min = 8, max = 16)
    String Title;
    @Min(value = 0)
    @Max(value = 10_000_000)
    int Price;
    @Size(min = 8, max = 64)
    String description;

}
