package ru.skypro.homework.mappers;

import org.mapstruct.Mapper;
import ru.skypro.homework.dto.CommentDto;
import ru.skypro.homework.model.Comment;

@Mapper
public interface CommentMapper {
    CommentDto toCommentDTO (Comment comment);

    Comment toComment(CommentDto commentDto);
}
