package ru.skypro.homework.mappers;

import org.mapstruct.Mapper;
import ru.skypro.homework.dto.Comment;
import ru.skypro.homework.entity.CommentEntity;

@Mapper(componentModel = "spring")
public interface CommentMapper {
    Comment toDTO(CommentEntity commentEntity);

    CommentEntity toModel(Comment comment);
}
