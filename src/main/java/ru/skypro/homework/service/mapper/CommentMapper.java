package ru.skypro.homework.service.mapper;

import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import ru.skypro.homework.dto.CommentDTO;
import ru.skypro.homework.model.Comment;

@Mapper(componentModel = "spring")
public interface CommentMapper {

    @Mapping(source = "id", target = "pk")
    @Mapping(source = "author.id", target = "author")
    //@Mapping(source = "author.imageModel", target = "authorImage")
    @Mapping(source = "author.firstName", target = "authorFirstName")
    CommentDTO commentToCommentDto(Comment comment);

    //Comment commentDtoToModel(CommentDTO commentDTO);
}
