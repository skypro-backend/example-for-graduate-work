package ru.skypro.homework.dto.comments;

import org.mapstruct.*;
import ru.skypro.homework.entity.comments.Comment;

import java.util.ArrayList;
import java.util.List;

@Mapper(componentModel = "spring")
public interface CommentMapper {
    Comment toCommentEntity(CommentDto commentDto);

    List<Comment> toCommentsList(List<CommentDto> commentDtos);

    default List<Comment> toListOfCommentsEntity(CommentsDto commentsDto) {
        if (commentsDto == null) {
            return null;
        }
        return toCommentsList(commentsDto.getResults());
    }

    Comment toCommentEntityFromCreateOrUpdateCommentDto(CreateOrUpdateCommentDto createOrUpdateCommentDto);

    CommentDto toCommentDto(Comment comment);

    List<CommentDto> toCommentDtoList(List<Comment> comments);

    default CommentsDto toCommentsDto(List<Comment> comments) {
        if (comments == null) {
            return null;
        }
        CommentsDto commentsDto = new CommentsDto();
        commentsDto.setResults(toCommentDtoList(comments));
        commentsDto.setCount(comments.size());
        return commentsDto;
    }

    CreateOrUpdateCommentDto toCreateOrUpdateDto(Comment comment);

    @BeanMapping(nullValuePropertyMappingStrategy = NullValuePropertyMappingStrategy.IGNORE)
    Comment partialUpdate(CommentDto commentDto, @MappingTarget Comment comment);
}