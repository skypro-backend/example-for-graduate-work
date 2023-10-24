package ru.skypro.homework.mapper;

import org.mapstruct.Mapper;
import org.mapstruct.MappingTarget;
import org.springframework.stereotype.Component;
import ru.skypro.homework.dto.CommentDto;
import ru.skypro.homework.model.Comment;

import java.util.List;

@Mapper
public interface CommentMapper {
    CommentDto commentToCommentDto(Comment comment);
    Comment commentDtoToComment(CommentDto commentDto);
    Comment updateCommentFromDto(CommentDto commentDto, @MappingTarget Comment comment);
    List<CommentDto> commentsToCommentDtos(List<Comment> comments);
}
