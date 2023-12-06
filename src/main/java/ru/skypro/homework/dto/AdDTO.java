package ru.skypro.homework.dto;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;
import net.bytebuddy.implementation.bind.annotation.Empty;
import ru.skypro.homework.model.Image;
import ru.skypro.homework.model.User;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Positive;
import javax.validation.constraints.Size;

@Data
@Schema(description = "объявление")
public class AdDTO {
    @Schema(description = "id автора объявления")
    private User author;
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
    @Size(min = 4, max = 100)
    private String title;
}
