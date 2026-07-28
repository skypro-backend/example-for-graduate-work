package ru.skypro.homework.mapper;

import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.ReportingPolicy;
import ru.skypro.homework.dto.Comment;
import ru.skypro.homework.dto.CreateOrUpdateComment;

@Mapper(componentModel = "spring", unmappedTargetPolicy = ReportingPolicy.IGNORE)
public interface CommentMapper {

    @Mapping(target = "pk", source = "id")
    @Mapping(target = "author", source = "author.id")
    @Mapping(target = "authorImage", source = "author.image")
    @Mapping(target = "authorFirstName", source = "author.firstName")
    @Mapping(target = "createdAt", expression = "java(entity.getCreatedAt().toInstant(java.time.ZoneOffset.UTC).toEpochMilli())")
    Comment toDto(ru.skypro.homework.entity.Comment entity);

    ru.skypro.homework.entity.Comment toEntity(CreateOrUpdateComment dto);
}