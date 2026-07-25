package com.skypro.dto;

import com.skypro.exception.GlobalExceptionHandler;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.http.HttpStatus;

/**
 * DTO для унифицированного ответа об ошибке.
 * <p>
 * Используется в {@link GlobalExceptionHandler}
 * для возврата клиенту понятных сообщений об ошибках с HTTP-статусом и временем возникновения.
 * </p>
 */

@Data
@AllArgsConstructor
@NoArgsConstructor
public class ErrorResponse {

    @Schema(description = "сообщение об ошибке")
    private String message;

    @Schema(description = "HTTP статус")
    private int status;

    @Schema(description = "временная метка")
    private long timestamp;

    public ErrorResponse(String message, HttpStatus status) {
        this.message = message;
        this.status = status.value();
        this.timestamp = System.currentTimeMillis();
    }
}
