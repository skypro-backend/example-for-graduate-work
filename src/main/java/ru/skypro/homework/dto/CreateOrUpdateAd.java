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

    @NotBlank(message = "Это поле не может быть пустым")
    @Size(min = 4, max = 32, message = "Заголовок объявления должен содержать не менее 4 и не более 32 символов")
    private String title;

    @Min(value = 0)
    @Max(value = 10_000_000)
    private int price;

    @NotBlank(message = "Это поле не может быть пустым")
    @Size(min = 8, max = 64, message = "Описание объявления должно содержать не менее 8 и не более 64 символов")
    private String description;

}
