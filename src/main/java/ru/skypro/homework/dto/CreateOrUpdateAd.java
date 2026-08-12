package ru.skypro.homework.dto;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;

import javax.validation.constraints.Max;
import javax.validation.constraints.Min;
import javax.validation.constraints.Size;

@Data
@Schema(description = "данные для создания или обновления объявления")
public class CreateOrUpdateAd {

    @Schema(description = "заголовок объявления")
    @Size(min = 4, max = 32)
    private String title;

    @Schema(description = "цена объявления")
    @Min(0)
    @Max(10000000)
    private Long price;

    @Schema(description = "описание объявления")
    @Size(min = 8, max = 64)
    private String description;
}
