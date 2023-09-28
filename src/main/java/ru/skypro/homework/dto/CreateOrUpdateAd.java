package ru.skypro.homework.dto;

import lombok.*;

import javax.validation.constraints.Max;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;

@Getter
@Setter
@EqualsAndHashCode
@ToString
@AllArgsConstructor
@NoArgsConstructor
public class CreateOrUpdateAd {

    @NotBlank
    @Size(min = 4, max = 32)
    private String title;

    @Min(value = 0)
    @Max(value = 10_000_000)
    private int price;

    @NotBlank
    @Size(min = 8, max = 64)
    private String description;

}
