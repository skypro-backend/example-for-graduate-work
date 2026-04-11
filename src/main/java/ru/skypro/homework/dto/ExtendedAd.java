package ru.skypro.homework.dto;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;
import lombok.Builder;
import lombok.NoArgsConstructor;
import lombok.AllArgsConstructor;

/**
 * DTO-класс, расширяющий модель объявления.
 * Включает дополнительную информацию о авторе, описание, контакты и изображение.
 */
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Schema(description = "Расширенная модель объявления")
public class ExtendedAd {

    @Schema(description = "id объявления", example = "101", requiredMode = Schema.RequiredMode.REQUIRED)
    private Integer pk;

    @Schema(description = "имя автора объявления", example = "Иван")
    private String authorFirstName;

    @Schema(description = "фамилия автора объявления", example = "Иванов")
    private String authorLastName;

    @Schema(description = "описание объявления", example = "Продаю новый телефон.")
    private String description;

    @Schema(description = "логин автора объявления", example = "ivan@mail.ru")
    private String email;

    @Schema(description = "ссылка на картинку объявления", example = "/images/ad1.jpg")
    private String image;

    @Schema(description = "телефон автора объявления", example = "+7 (999) 123-45-67")
    private String phone;

    @Schema(description = "цена объявления", example = "50000")
    private Integer price;

    @Schema(description = "заголовок объявления", example = "Продам iPhone 15 Pro")
    private String title;
}
