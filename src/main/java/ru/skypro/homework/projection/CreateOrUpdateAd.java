package ru.skypro.homework.projection;

import lombok.Data;
import lombok.RequiredArgsConstructor;

import javax.validation.constraints.Max;
import javax.validation.constraints.Min;
import javax.validation.constraints.Size;

@RequiredArgsConstructor
@Data
public class CreateOrUpdateAd {
    @Size(min = 4, max = 32)
    private final String title;
    @Min(value = 0) @Max(value = 10_000_000)
    private final Integer price;
    @Size(min = 4, max = 32)
    private final String description;
}
