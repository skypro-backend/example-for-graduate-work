package ru.skypro.homework.mapper;

import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import ru.skypro.homework.dto.comment.Comment;
import ru.skypro.homework.dto.comment.CreateOrUpdateComment;
import ru.skypro.homework.entity.CommentEntity;

import java.time.LocalDateTime;
import java.time.ZoneOffset;

@Mapper(componentModel = "spring")
public interface CommentMapper {


    @Mapping(source = "id", target = "pk")
    @Mapping(source = "text", target = "text")
    @Mapping(source = "author.id", target = "author")
    @Mapping(source = "author.firstName", target = "authorFirstName")
    @Mapping(source = "author", target = "authorImage", ignore = true)
    @Mapping(target = "createdAt", expression = "java(toEpochMillis(entity.getCreatedAt()))")
    Comment toDto(CommentEntity entity);


    @Mapping(target = "id", ignore = true)
    @Mapping(target = "author", ignore = true)
    @Mapping(target = "ad", ignore = true)
    @Mapping(target = "createdAt", ignore = true)
    CommentEntity toEntity(CreateOrUpdateComment dto);


    default Long toEpochMillis(LocalDateTime dateTime) {
        return dateTime == null ? null :
                dateTime.toInstant(ZoneOffset.UTC).toEpochMilli();
    }
}
