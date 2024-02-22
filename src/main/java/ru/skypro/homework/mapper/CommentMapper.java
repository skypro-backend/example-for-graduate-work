package ru.skypro.homework.mapper;

import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.NullValueMappingStrategy;
import org.mapstruct.factory.Mappers;
import ru.skypro.homework.dto.Comment;
import ru.skypro.homework.dto.CreateOrUpdateComment;
import ru.skypro.homework.entity.CommentEntity;

/**
 * CommentMapper
 * <br><i>содержит методы:</i>
 * <br>-toEntity<i></i>
 * <br>-toCommentDto<i></i>
 * <br>-toCommentsDto<i></i>
 * <br>-toCreateOrUpdateCommentDto<i></i>
 */
@Mapper(componentModel = "string",
        nullValueMappingStrategy = NullValueMappingStrategy.RETURN_DEFAULT)
public interface CommentMapper {

    CommentMapper INSTANCE = Mappers.getMapper(CommentMapper.class);

    // to use CommentMapper.INSTANCE.toEntity(---);
    // to use CommentMapper.INSTANCE.toDto___();

    //_____ toEntity___
    @Mapping(source = "author", target = "id")
    @Mapping(source = "pk", target = "pk.id")
    @Mapping(source = "authorImage", target = "author.image.id")
    @Mapping(source = "authorFirstName", target = "author.firstName")
    CommentEntity toEntity(Comment dto);

//    CommentEntity toEntity(Comments dto);

    CommentEntity toEntity(CreateOrUpdateComment dto);

    //_____ toDto___
    @Mapping(target = "author", source = "id")
    @Mapping(target = "pk", source = "pk.id")
    @Mapping(target = "authorImage", source = "author.image.id")
    @Mapping(target = "authorFirstName", source = "author.firstName")
    Comment toCommentDto(CommentEntity entity);

//    Comments toCommentsDto(CommentEntity entity);

    CreateOrUpdateComment toCreateOrUpdateCommentDto(CommentEntity entity);
}
