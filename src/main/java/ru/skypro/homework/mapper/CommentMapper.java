package ru.skypro.homework.mapper;

import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.springframework.beans.factory.annotation.Autowired;
import ru.skypro.homework.dto.CommentDto;
import ru.skypro.homework.dto.CreateOrUpdateCommentDto;
import ru.skypro.homework.models.Comment;

@Mapper
public abstract class CommentMapper {

    @Autowired
    public UserMapper userMapper;

    @Mapping(target = "pk", source = "id")
    @Mapping(target = "author", source = "user.id")
    @Mapping(target = "authorFirstName", source = "user.firstName")
    @Mapping(target = "authorImage", expression = "java(userMapper.getImageUri(source.getUser()))")
    public abstract CommentDto convertToDto(Comment source);

    public abstract Comment convertToEntity(CreateOrUpdateCommentDto source);

}
