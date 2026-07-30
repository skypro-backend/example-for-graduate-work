package ru.skypro.homework.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.Max;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

/**
 * Данные создания или изменения объявления.
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
public class CreateOrUpdateAd {

    @NotBlank
    @Size(min = 4, max = 32)
    private String title;

    @NotNull
    @Min(0)
    @Max(10_000_000)
    private Integer price;

    @NotBlank
    @Size(min = 8, max = 64)
    private String description;
}
