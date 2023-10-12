package ru.skypro.homework.dto.CommentsDtos;

import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
public class CommentsListDto {
    private int Count;
    private List<CommentDto> Results;
}
