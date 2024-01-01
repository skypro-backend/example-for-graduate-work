package ru.skypro.kakavito.exceptions;

public class ImageSizeExceededException extends Throwable {
    public ImageSizeExceededException(String message, long MAX_SIZE) {
    }

    public ImageSizeExceededException(String message) {
        super(message);
    }

    public ImageSizeExceededException(String message, Throwable cause) {
        super(message, cause);
    }

    public ImageSizeExceededException(Throwable cause) {
        super(cause);
    }

    public ImageSizeExceededException(String message, Throwable cause, boolean enableSuppression, boolean writableStackTrace) {
        super(message, cause, enableSuppression, writableStackTrace);
    }
}
