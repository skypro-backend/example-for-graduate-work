package ru.skypro.homework.projection;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.Max;
import javax.validation.constraints.Min;
import javax.validation.constraints.Size;

@AllArgsConstructor
@NoArgsConstructor
@Data
public class CreateOrUpdateAd {
    @Size(min = 4, max = 32)
    private String title;
    @Min(value = 0) @Max(value = 10_000_000)
    private Integer price;
    @Size(min = 4, max = 32)
    private String description;
}
