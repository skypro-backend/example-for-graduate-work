package ru.skypro.homework.model.utils;

import lombok.Data;
import org.springframework.http.HttpStatus;
import ru.skypro.homework.dto.CommentDto;

/**
 * <h2>CommentDtoWithHttpStatus</h2>
 * Util calss containing DTO of resulted by CRUD operation Comment entity and HttpStatus
 */
@Data
public class CommentDtoWithHttpStatus {
    CommentDto commentDto;
    HttpStatus httpStatus;

    public static CommentDtoWithHttpStatus notFound() {
        CommentDtoWithHttpStatus result = new CommentDtoWithHttpStatus();
        result.setCommentDto(null);
        result.setHttpStatus(HttpStatus.NOT_FOUND);
        return result;
    }

    public static CommentDtoWithHttpStatus unAuthorized() {
        CommentDtoWithHttpStatus result = new CommentDtoWithHttpStatus();
        result.setCommentDto(null);
        result.setHttpStatus(HttpStatus.UNAUTHORIZED);
        return result;
    }
}
