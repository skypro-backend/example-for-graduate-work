package ru.skypro.homework.model.utils;

import lombok.Data;
import org.springframework.http.HttpStatus;
import ru.skypro.homework.model.Images;

@Data
public class ImageProcessResult {
    private Images image;
    private long imageId;
    private HttpStatus httpStatus;
}
