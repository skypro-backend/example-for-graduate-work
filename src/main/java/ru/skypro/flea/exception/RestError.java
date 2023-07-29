package ru.skypro.flea.exception;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class RestError {

    private String httpStatus;

    private String message;

}
