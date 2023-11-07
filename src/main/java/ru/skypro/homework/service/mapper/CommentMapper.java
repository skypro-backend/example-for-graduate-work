package ru.skypro.homework.service.mapper;

import org.mapstruct.Mapper;
import ru.skypro.homework.dto.CommentDTO;
import ru.skypro.homework.model.Comment;

@Mapper(componentModel = "spring")
public interface CommentMapper {

    CommentDTO commentToDto(Comment comment);

    Comment commentDtoToModel(CommentDTO commentDTO);
}
