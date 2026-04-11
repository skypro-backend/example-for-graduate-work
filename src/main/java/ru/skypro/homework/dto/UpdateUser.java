package ru.skypro.homework.dto;
import io.swagger.v3.oas.annotations.media.Schema;
import javax.validation.constraints.Pattern;
import javax.validation.constraints.Size;
import lombok.Data;
import lombok.Builder;
import lombok.NoArgsConstructor;
import lombok.AllArgsConstructor;

/**
 * DTO-класс для обновления данных пользователя.
 * Включает имя, фамилию и телефон с валидациями.
 */
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Schema(description = "Модель для обновления данных пользователя")
public class UpdateUser {

    @Size(min = 3, max = 10, message = "Имя должно быть от 3 до 10 символов")
    @Schema(description = "имя пользователя", example = "Иван", minLength = 3, maxLength = 10)
    private String firstName;

    @Size(min = 3, max = 10, message = "Фамилия должна быть от 3 до 10 символов")
    @Schema(description = "фамилия пользователя", example = "Иванов", minLength = 3, maxLength = 10)
    private String lastName;

    @Pattern(regexp = "^(\\+7|8)?[\\s()-]*\\d{3}[\\s()-]*\\d{3}[\\s()-]*\\d{2}[\\s()-]*\\d{2}$",
            message = "Некорректный формат телефона")
    @Schema(description = "телефон пользователя",
            example = "+7 (999) 123-45-67",
            pattern = "^(\\+7|8)?[\\s()-]*\\d{3}[\\s()-]*\\d{3}[\\s()-]*\\d{2}[\\s()-]*\\d{2}$")
    private String phone;
}
