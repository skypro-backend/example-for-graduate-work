package ru.skypro.homework.model.mapper;

import org.mapstruct.Mapper;
import ru.skypro.homework.model.dto.CommentDto;
import ru.skypro.homework.model.entity.Comment;

@Mapper
public interface CommentMapper {
    CommentDto commentToCommentDto (Comment comment);
    Comment CommentDtoToComment(CommentDto commentDto);
}
