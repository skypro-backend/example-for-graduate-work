package ru.skypro.homework.mapper;

import org.mapstruct.*;

import ru.skypro.homework.comment.Comment;
import ru.skypro.homework.comment.CreateOrUpdateComment;
import ru.skypro.homework.entity.CommentEntity;

import java.time.Instant;

@Mapper(componentModel = "spring")
public interface CommentMapper {

    @Mappings({
            @Mapping(source = "id", target = "pk"),
            @Mapping(source = "text", target = "text"),
            @Mapping(source = "createdAt", target = "createdAt"),
            @Mapping(source = "author.id", target = "author"),
            @Mapping(source = "author.imageUrl", target = "authorImage"),
            @Mapping(source = "author.firstName", target = "authorFirstName")
    })
    Comment entityToDto(CommentEntity entity);

    @Mappings({
            @Mapping(target = "id", ignore = true),
            @Mapping(target = "author", ignore = true),
            @Mapping(target = "ad", ignore = true),
            @Mapping(target = "createdAt", ignore = true)
    })
    CommentEntity dtoToEntity(CreateOrUpdateComment dto);

    @AfterMapping
    default void initCreatedAt(@MappingTarget CommentEntity dest) {
        dest.setCreatedAt(Instant.now().toEpochMilli());
    }

    @InheritInverseConfiguration
    @Mappings({
            @Mapping(target = "createdAt", ignore = true)
    })
    void updateFromDto(CreateOrUpdateComment source, @MappingTarget CommentEntity dest);
}