package ru.skypro.homework.dto;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Schema(description = "Ответ с ошибкой")
public class ErrorResponse {

    @Schema(description = "Код ошибки")
    private int statusCode;

    @Schema(description = "Сообщение об ошибке")
    private String message;

    @Schema(description = "Временная метка")
    private long timestamp;

    @Schema(description = "Путь запроса")
    private String path;

    @Schema(description = "Тип ошибки")
    private String error;

    public ErrorResponse(int statusCode, String message, String path, String error) {
        this.statusCode = statusCode;
        this.message = message;
        this.timestamp = System.currentTimeMillis();
        this.path = path;
        this.error = error;
    }
}