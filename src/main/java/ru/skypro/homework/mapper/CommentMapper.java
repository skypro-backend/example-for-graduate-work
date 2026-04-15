package ru.skypro.homework.mapper;

import org.mapstruct.*;
import ru.skypro.homework.dto.CommentDto;
import ru.skypro.homework.dto.CreateOrUpdateCommentDto;
import ru.skypro.homework.entity.Comment;

@Mapper(componentModel = "spring")
public interface CommentMapper {

    @Mapping(source = "author.id", target = "author")
    @Mapping(source = "author.image", target = "authorImage")
    @Mapping(source = "author.firstName", target = "authorFirstName")
    CommentDto toDto(Comment comment);

    @Mapping(target = "author", ignore = true)
    @Mapping(target = "ad", ignore = true)
    Comment toEntity(CommentDto commentDto);

    @BeanMapping(nullValuePropertyMappingStrategy = NullValuePropertyMappingStrategy.IGNORE)
    @Mapping(target = "author", ignore = true)
    @Mapping(target = "ad", ignore = true)
    void updateEntityFromDto(CreateOrUpdateCommentDto createOrUpdateCommentDto, @MappingTarget Comment comment);
}
