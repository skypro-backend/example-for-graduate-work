package ru.skypro.homework.mapper;

import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.factory.Mappers;
import org.springframework.beans.factory.annotation.Autowired;
import ru.skypro.homework.dto.CommentDto;
import ru.skypro.homework.model.Comment;

import java.util.List;


@Mapper(componentModel = "spring")
public interface CommentMapper {

    CommentMapper INSTANCE = Mappers.getMapper(CommentMapper.class);

    @Mapping(source = "author.id", target = "author")
    @Mapping(source = "author.image", target = "authorImage")
    @Mapping(source = "author.firstName", target = "authorFirstName")
    @Mapping(source = "id", target = "pk")
    CommentDto commentToCommentDto(Comment comment);

    @Mapping(source = "author", target = "author.id")
    @Mapping(source = "authorImage", target = "author.image")
    @Mapping(source = "authorFirstName", target = "author.firstName")
    @Mapping(target = "id", source = "pk")
    Comment commentDtoToComment(CommentDto commentDto);
}
