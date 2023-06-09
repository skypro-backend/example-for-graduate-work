package ru.skypro.homework.mapper;

import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.factory.Mappers;
import ru.skypro.homework.dto.CommentDto;
import ru.skypro.homework.model.Comment;

import java.util.List;


@Mapper(componentModel = "spring")
public interface CommentMapper {
// Закомментировал так как были ошибки при запуске приложения
//    CommentMapper INSTANCE = Mappers.getMapper(CommentMapper.class);
//
//    @Mapping(source = "id", target = "pk")
//    CommentDto commentToCommentDto(Comment comment);
//
//    @Mapping(target = "id", source = "pk")
//    Comment commentDtoToComment(CommentDto commentDto);
}
