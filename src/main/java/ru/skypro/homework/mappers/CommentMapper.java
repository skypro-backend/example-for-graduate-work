package ru.skypro.homework.mappers;

import org.mapstruct.*;
import ru.skypro.homework.dto.comments.CommentDto;
import ru.skypro.homework.dto.comments.CommentsDto;
import ru.skypro.homework.dto.comments.CreateOrUpdateCommentDto;
import ru.skypro.homework.entity.comments.Comment;
import ru.skypro.homework.entity.users.User;

import java.time.Instant;
import java.time.LocalDateTime;
import java.time.ZoneId;
import java.util.List;

@Mapper(componentModel = "spring", uses = CustomMapper.class)
public interface CommentMapper {

    Comment toCommentEntity(CommentDto commentDto);

    default LocalDateTime mapCreatedAt(Integer createdAt) {
        return Instant.ofEpochSecond(createdAt)
                .atZone(ZoneId.systemDefault())
                .toLocalDateTime();
    }

    List<Comment> toCommentsList(List<CommentDto> commentDtos);

    default List<Comment> toListOfCommentsEntity(CommentsDto commentsDto) {
        if (commentsDto == null) {
            return null;
        }
        return toCommentsList(commentsDto.getResults());
    }

    Comment toCommentEntityFromCreateOrUpdateCommentDto(CreateOrUpdateCommentDto createOrUpdateCommentDto);

    CommentDto toCommentDto(Comment comment);

    default Integer map(User user) {
        if (user == null) {
            return null;
        }
        return user.getId();
    }

    default Integer mapDateToInteger(LocalDateTime createdAt) {
        return (int) createdAt.atZone(ZoneId.systemDefault()).toEpochSecond();
    }

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

}