package ru.skypro.homework.dto;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.*;
import lombok.Data;
import net.bytebuddy.implementation.bind.annotation.Empty;

@Data
@Schema(description = "объявление")
public class AdDTO {
    @Schema(description = "id автора объявления")
    private int author;
    @Schema(description = "ссылка на картинку объявления")
    @NotBlank
    private String image;
    @Schema(description = "id объявления")
    private int pk;
    @Schema(description = "цена объявления")
    @NotNull
    @Positive
    private int price;
    @Schema(description = "заголовок объявления")
    @NotBlank
    @Size(min = 4, max = 25)
    private String title;
}
