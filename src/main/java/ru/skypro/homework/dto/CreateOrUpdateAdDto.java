package ru.skypro.homework.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.*;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class CreateOrUpdateAdDto {

    @Size(min = 4, max = 32, message = "Заголовок объявления должен содержать от 4 до 32 символов")
    private String title;

    @Min(value = 0, message = "Цена не может быть отрицательной")
    @Max(value = 10000000, message = "Цена не может превышать 10 000 000")
    private Integer price;

    @Size(min = 8, max = 64, message = "Описание объявления должно содержать от 8 до 64 символов")
    private String description;
}
