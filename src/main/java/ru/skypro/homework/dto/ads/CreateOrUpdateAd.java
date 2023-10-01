package ru.skypro.homework.dto.ads;

import lombok.Data;

import javax.validation.constraints.Max;
import javax.validation.constraints.Min;
import javax.validation.constraints.Size;

@Data
public class CreateOrUpdateAd {
    @Size(min = 4, max = 32)
    private String title;

    private int price;
    @Size(min = 8, max = 64)
    private String description;

}
