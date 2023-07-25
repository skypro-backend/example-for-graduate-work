package ru.skypro.flea.exception.handler;

import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import ru.skypro.flea.controller.impl.UserApiController;
import ru.skypro.flea.exception.RestError;

import javax.security.sasl.AuthenticationException;

@RestControllerAdvice(basePackageClasses = {
        UserApiController.class
})
public class AuthenticationExceptionHandler {

    @ExceptionHandler(AuthenticationException.class)
    @ApiResponse(
            responseCode = "401",
            description = "Unauthorized",
            content = @Content(
                    mediaType = MediaType.APPLICATION_JSON_VALUE,
                    schema = @Schema(
                            implementation = RestError.class
                    )
            )
    )
    public ResponseEntity<RestError> handleAuthenticationException(Exception ex) {
        RestError re = new RestError(HttpStatus.UNAUTHORIZED.toString(),
                "Authentication failed at controller advice");
        return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body(re);
    }

}
