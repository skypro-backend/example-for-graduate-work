package ru.skypro.homework.dto;

import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.Max;
import javax.validation.constraints.Min;
import javax.validation.constraints.Size;

@Data
public class CreateOrUpdateAd {

    @Size(min = 4, max = 32, message = "Заголовок объявления должен содержать от 4 до 32 символов")
    private String title;

    @Min(value = 0, message = "Цена не может быть отрицательной")
    @Max(value = 10000000, message = "Цена не может превышать 10 000 000")
    private Integer price;

    @Size(min = 8, max = 64, message = "Описание объявления должно содержать от 8 до 64 символов")
    private String description;
}
