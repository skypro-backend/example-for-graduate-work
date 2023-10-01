package ru.skypro.homework.dto.ads;

import lombok.Data;

import javax.validation.constraints.Max;
import javax.validation.constraints.Min;
import javax.validation.constraints.Size;

@Data
public class CreateOrUpdateAd {
    private String Title;
    private int Price;
    private String description;

}
