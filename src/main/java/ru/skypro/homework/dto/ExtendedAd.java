package ru.skypro.homework.dto;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class ExtendedAd {

    @Schema(
            description = "Уникальный ID объявления (генерируется сервером)",
            example = "505",
            accessMode = Schema.AccessMode.READ_ONLY
    )
    private Integer pk;

    @Schema(description = "Имя автора объявления", example = "Сергей")
    @Size(min = 2, max = 50, message = "Имя должно быть от 2 до 50 символов")
    private String authorFirstName;

    @Schema(description = "Фамилия автора объявления", example = "Петров")
    @Size(min = 2, max = 50, message = "Фамилия должна быть от 2 до 50 символов")
    private String authorLastName;

    @Schema(description = "Полное описание товара", example = "Продаю велосипед Trek...")
    @Size(max = 1000, message = "Описание не должно превышать 1000 символов")
    private String description;

    @Schema(description = "Email продавца для связи", example = "sergey.petrov@mail.ru")
    @Email(message = "Некорректный формат email")
    private String email;

    @Schema(description = "Ссылка на главное фото товара", example = "https://cdn.skypro.ru/ads/505/main.jpg")
    // Для ссылок нет стандартной простой аннотации, можно оставить просто Schema
    private String image;

    @Schema(description = "Телефон продавца", example = "+79991234567")
    private String phone;

    @Schema(description = "Цена товара в рублях", example = "35000")
    @Min(value = 0, message = "Цена не может быть отрицательной")
    private Integer price;

    @Schema(description = "Заголовок объявления", example = "Велосипед Trek, новый, рама M")
    @Size(min = 3, max = 100, message = "Заголовок должен быть от 3 до 100 символов")
    private String title;
}
