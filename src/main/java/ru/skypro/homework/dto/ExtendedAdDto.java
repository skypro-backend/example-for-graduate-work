package ru.skypro.homework.dto;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;


@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Schema(description = "Полная информация об объявлении")
public class ExtendedAdDto {

    @Schema(description = "ID объявления", example = "1")
    private Integer pk;

    @Schema(description = "Имя автора", example = "Иван")
    private String authorFirstName;

    @Schema(description = "Фамилия автора", example = "Иванов")
    private String authorLastName;

    @Schema(description = "Описание объявления", example = "Гараж в хорошем состоянии")
    private String description;

    @Schema(description = "Email автора", example = "user@example.com")
    private String email;

    @Schema(description = "Ссылка на картинку объявления", example = "/images/abc123.jpg")
    private String image;

    @Schema(description = "Телефон автора", example = "+79161234567")
    private String phone;

    @Schema(description = "Цена объявления", example = "15000")
    private Integer price;

    @Schema(description = "Заголовок объявления", example = "Продам гараж")
    private String title;
}