package ru.skypro.homework.dto;

import lombok.Data;
import ru.skypro.homework.model.Comment;

import java.util.List;
@Data
public class ResponseWrapperCommentDto {
    private Integer count;

    private List<Comment> results;
}
