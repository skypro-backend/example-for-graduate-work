package ru.skypro.homework.dto;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;
import lombok.Builder;
import lombok.NoArgsConstructor;
import lombok.AllArgsConstructor;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Schema(description = "Модель пользователя")
public class User {

    @Schema(description = "id пользователя", example = "1", requiredMode = Schema.RequiredMode.REQUIRED)
    private Integer id;

    @Schema(description = "логин пользователя", example = "user@example.com", requiredMode = Schema.RequiredMode.REQUIRED)
    private String email;

    @Schema(description = "имя пользователя", example = "Иван")
    private String firstName;

    @Schema(description = "фамилия пользователя", example = "Иванов")
    private String lastName;

    @Schema(description = "телефон пользователя", example = "+7 (999) 123-45-67")
    private String phone;

    @Schema(description = "роль пользователя", allowableValues = {"USER", "ADMIN"},
            example = "USER", requiredMode = Schema.RequiredMode.REQUIRED)
    private String role;

    @Schema(description = "ссылка на аватар пользователя", example = "https://example.com/user.jpg")
    private String image;
}
