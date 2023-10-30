package ru.skypro.homework.mapper;

import lombok.RequiredArgsConstructor;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import ru.skypro.homework.dto.CommentDto;
import ru.skypro.homework.dto.CreateOrUpdateCommentDto;
import ru.skypro.homework.models.Comment;

@Mapper
@RequiredArgsConstructor
public abstract class CommentMapper {

    private final UserMapper userMapper;

    @Mapping(target = "author", source = "user.id")
    @Mapping(target = "authorFirstName", source = "user.firstName")
    @Mapping(target = "authorImage", expression = "java(userMapper.setImageURI(entity.getUser()))")
    public abstract CommentDto toDto(Comment entity);

    public abstract Comment toEntity(CreateOrUpdateCommentDto dto);

}
