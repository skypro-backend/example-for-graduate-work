package ru.skypro.homework.mapper;

import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.MappingTarget;
import ru.skypro.homework.dto.response.CommentResponseDto;
import ru.skypro.homework.dto.response.CommentsResponseDto;
import ru.skypro.homework.dto.request.CommentRequestDto;
import ru.skypro.homework.entity.Comment;
import ru.skypro.homework.entity.User;

import java.util.List;

@Mapper(componentModel = "spring")
public interface CommentMapper {
    @Mapping(source = "id", target = "pk")
    @Mapping(source = "author.firstName", target = "authorFirstName")
    @Mapping(source = "author.image", target = "authorImage")
    CommentResponseDto commentToDto(Comment comment);

    List<CommentResponseDto> commentsToDtoList(List<Comment> comments);

    default CommentsResponseDto toCommentsResponseDto(List<Comment> comments) {
        return new CommentsResponseDto(comments.size(), commentsToDtoList(comments));
    }

    default Integer map(User user) {
        return user != null ? user.getId() : null;
    }

    Comment commentRequestDtoToComment(CommentRequestDto dto);

    void updateCommentFromDto(CommentRequestDto dto, @MappingTarget Comment comment);
}
