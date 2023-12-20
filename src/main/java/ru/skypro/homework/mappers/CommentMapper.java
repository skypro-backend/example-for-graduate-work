package ru.skypro.homework.mappers;

import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.factory.Mappers;
import ru.skypro.homework.dto.CommentDTO;
import ru.skypro.homework.entity.Comment;
import ru.skypro.homework.entity.User;

@Mapper(componentModel = "spring")
public interface CommentMapper {

    CommentMapper INSTANCE = Mappers.getMapper(CommentMapper.class);

    // Преобразование Comment в CommentDTO
    @Mapping(target = "author", source = "user.id")
    @Mapping(target = "authorFirstName", source = "user.firstName")
    @Mapping(target = "authorImage", expression = "java(\"/image/\" + user.getImage().getId())")
    @Mapping(target = "pk", source = "comment.id")
    CommentDTO toCommentDTO (Comment comment, User user);
}
