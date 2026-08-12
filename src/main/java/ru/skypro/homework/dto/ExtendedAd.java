package ru.skypro.homework.dto;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;

@Data
@Schema(description = "полная информация об объявлении")
public class ExtendedAd {

    @Schema(description = "id объявления")
    private Long pk;

    @Schema(description = "имя автора объявления")
    private String authorFirstName;

    @Schema(description = "фамилия автора объявления")
    private String authorLastName;

    @Schema(description = "описание объявления")
    private String description;

    @Schema(description = "логин автора объявления")
    private String email;

    @Schema(description = "ссылка на картинку объявления")
    private String image;

    @Schema(description = "номер телефона автора объявления")
    private String phone;

    @Schema(description = "цена объявления")
    private Long price;

    @Schema(description = "заголовок объявления")
    private String title;
}
