package ru.skypro.homework.model.utils;

import lombok.Data;
import org.springframework.http.HttpStatus;
import ru.skypro.homework.dto.CommentsDto;

/**
 * <h2>CommentsDtoFound</h2>
 * Util class<br>
 * Instance of CommentsDtoFound contain {@link ru.skypro.homework.dto.CommentsDto}
 * and {@link org.springframework.http.HttpStatus} of data base search result
 */
@Data
public class CommentsDtoFound {
    private CommentsDto commentsDto;
    private HttpStatus httpStatus;
}
