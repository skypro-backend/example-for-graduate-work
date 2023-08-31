package ru.skypro.homework.dto.comments;

import org.mapstruct.*;
import ru.skypro.homework.entity.comments.Comment;

import java.util.List;

@Mapper(componentModel = "spring")
public interface CommentMapper {
    Comment toCommentEntity(CommentDto commentDto);

    List<Comment> toListOfCommentsEntity(CommentsDto commentsDto);

    Comment toCommentEntityFromCreateOrUpdateCommentDto(CreateOrUpdateCommentDto createOrUpdateCommentDto);

    CommentDto toCommentDto(Comment comment);

    CommentsDto toCommentsDto(List<Comment> comments);

    CreateOrUpdateCommentDto toCreateOrUpdateDto(Comment comment);

    @BeanMapping(nullValuePropertyMappingStrategy = NullValuePropertyMappingStrategy.IGNORE)
    Comment partialUpdate(CommentDto commentDto, @MappingTarget Comment comment);
}