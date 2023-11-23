package ru.skypro.homework.mapper;

import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.factory.Mappers;
import ru.skypro.homework.dto.Comment;
import ru.skypro.homework.entity.CommentEntity;

@Mapper
public interface CommentMapper {

    CommentMapper INSTANCE = Mappers.getMapper(CommentMapper.class);

    @Mapping(source = "author.id", target = "author")
    Comment commentToCommentDTO(CommentEntity commentEntity);

    @Mapping(source = "author", target = "author.id")
    CommentEntity commentDTOToComment(Comment commentDTO);
}