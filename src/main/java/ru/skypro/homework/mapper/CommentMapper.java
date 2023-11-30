package ru.skypro.homework.mapper;

import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.factory.Mappers;
import ru.skypro.homework.dto.CreateOrUpdateComment;
import ru.skypro.homework.entity.CommentEntity;

@Mapper
public interface CommentMapper {

    CommentMapper INSTANCE = Mappers.getMapper(CommentMapper.class);

    @Mapping(target = "author", ignore = true)
    @Mapping(target = "ad", ignore = true)
    @Mapping(target = "createdAt", expression = "java(Instant.now())")
    @Mapping(target = "authorImage", source = "author.image")
    @Mapping(target = "authorFirstName", source = "author.firstName")
    CommentEntity createOrUpdateCommentToCommentEntity(CreateOrUpdateComment createOrUpdateComment);

    @Mapping(target = "pk", ignore = true)
    @Mapping(target = "author", ignore = true)
    @Mapping(target = "ad", ignore = true)
    @Mapping(target = "createdAt", expression = "java(Instant.now())")
    @Mapping(target = "authorImage", source = "author.image")
    @Mapping(target = "authorFirstName", source = "author.firstName")
    CommentEntity updateCommentEntityFromCreateOrUpdateComment(CommentEntity existingCommentEntity, CreateOrUpdateComment createOrUpdateComment);

    @Mapping(target = "text", source = "text")
    CommentEntity commentEntityFromCreateOrUpdateComment(CreateOrUpdateComment createOrUpdateComment);

    @Mapping(target = "text", source = "text")
    CreateOrUpdateComment createOrUpdateCommentFromCommentEntity(CommentEntity commentEntity);
}
