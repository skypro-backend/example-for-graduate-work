package ru.skypro.homework.dto.ads;

import lombok.Data;

import javax.validation.constraints.Max;
import javax.validation.constraints.Min;
import javax.validation.constraints.Size;

@Data
public class CreateOrUpdateAdDto {
    @Size(min = 4, max = 32)
    private String title;
    @Min(value = 0, message = "Цена не может быть отрицательной")
    @Max(value = 10_000_000, message = "Цена не может быть больше 10 млн.")
    private Integer price;
    @Size(min = 8, max = 64, message = "Ограничение: от 8 до 64 символов")
    private String description;
}
