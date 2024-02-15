package ru.skypro.homework.mapper;

import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.NullValueMappingStrategy;
import ru.skypro.homework.dto.Comment;
import ru.skypro.homework.entity.CommentEntity;

/**
 * CommentMapper
 * <br><i>содержит методы</i>
 * <br>- toDTO<i>(из {@link CommentEntity} в {@link Comment})</i>
 * <br>- toEntity<i>(из {@link Comment} в {@link CommentEntity})</i>
 */
@Mapper(componentModel = "string",
        nullValueMappingStrategy = NullValueMappingStrategy.RETURN_DEFAULT)
public interface CommentMapper {

    @Mapping(target = "author", source = "author")
    @Mapping(target = "authorImage", source = "authorImage")
    @Mapping(target = "authorFirstName", source = "authorFirstName")
    @Mapping(target = "createAt", source = "createAt")
    @Mapping(target = "pk", source = "pk")
    @Mapping(target = "text", source = "text")
    @Mapping(target = "count", source = "count")
    CommentEntity toDTO(CommentEntity commentEntity);

    @Mapping(target = "author", source = "author")
    @Mapping(target = "authorImage", source = "authorImage")
    @Mapping(target = "authorFirstName", source = "authorFirstName")
    @Mapping(target = "createAt", source = "createAt")
    @Mapping(target = "pk", source = "pk")
    @Mapping(target = "text", source = "text")
    @Mapping(target = "count", source = "count")
    CommentEntity toEntity(Comment comment);
}
