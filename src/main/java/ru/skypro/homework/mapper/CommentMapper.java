package ru.skypro.homework.mapper;

import org.mapstruct.*;
import ru.skypro.homework.dto.Comment;
import ru.skypro.homework.entity.CommentEntity;

import java.time.ZoneId;

@Mapper(componentModel = "spring", imports = {ZoneId.class})
public interface CommentMapper {

    @Mapping(source = "pk", target = "pk")
    @Mapping(source = "text", target = "text")
    @Mapping(source = "author.id", target = "author")
    @Mapping(source = "author.firstName", target = "authorFirstName")
    @Mapping(expression = "java(\"/users/\" + commentEntity.getAuthor().getId() + \"/image\")", target = "authorImage")
    @Mapping(expression = "java(commentEntity.getCreatedAt().atZone(ZoneId.systemDefault()).toInstant().toEpochMilli())", target = "createdAt")
    Comment toDto(CommentEntity commentEntity);

    @Mapping(target = "pk", ignore = true)
    @Mapping(target = "createdAt", expression = "java(java.time.LocalDateTime.now())")
    @Mapping(target = "ad", ignore = true)
    @Mapping(target = "author", ignore = true)
    CommentEntity toEntity(Comment comment);
}