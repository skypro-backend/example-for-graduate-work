package ru.skypro.homework.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.Max;
import javax.validation.constraints.Min;
import javax.validation.constraints.Size;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class CreateOrUpdateAd {

    @Size(min = 8, max = 16)
    private String title;

    @Min(value = 0)
    @Max(value = 10_000_000)
    private int price;

    @Size(min = 8, max = 64)
    private String description;

}
