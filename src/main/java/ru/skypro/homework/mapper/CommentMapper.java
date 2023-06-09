package ru.skypro.homework.mapper;

import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import ru.skypro.homework.dto.CommentDto;
import ru.skypro.homework.model.Comment;

import java.util.List;


@Mapper(componentModel = "spring")
public interface CommentMapper {

    @Mapping(source = "id", target = "author")
    @Mapping(source = "pk", target = "pk")
    CommentDto commentToCommentDto(Comment comment);

    List<CommentDto> commentDto(List<Comment> comments);

    @Mapping(source = "author", target = "id")
    @Mapping(source = "pk", target = "pk")
//    @Mapping(target = "id", ignore = true)
    @Mapping(target = "createdAt", ignore = true)
    Comment commentDtoToComment(CommentDto commentDto);

    List<Comment> commentDtoToComment(List<CommentDto> CommentDto);
}
