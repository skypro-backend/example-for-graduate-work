package ru.skypro.homework.mapper;

import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.MappingTarget;
import ru.skypro.homework.dto.Comment;
import ru.skypro.homework.dto.Comments;
import ru.skypro.homework.dto.CreateOrUpdateComment;
import ru.skypro.homework.entity.CommentEntity;
import ru.skypro.homework.entity.UserEntity;
import ru.skypro.homework.entity.AdEntity;
import java.util.List;

@Mapper(componentModel = "spring")
public interface CommentMapper {

    @Mapping(source = "author.id", target = "author")
    @Mapping(source = "author.image", target = "authorImage")
    @Mapping(source = "author.firstName", target = "authorFirstName")
    @Mapping(source = "id", target = "pk")
    @Mapping(source = "createdAt", target = "createdAt")
    Comment toDto(CommentEntity commentEntity);

    List<Comment> toDtoList(List<CommentEntity> commentEntities);

    default Comments toCommentsDto(List<CommentEntity> commentEntities) {
        Comments comments = new Comments();
        comments.setCount(commentEntities.size());
        comments.setResults(toDtoList(commentEntities));
        return comments;
    }

    @Mapping(target = "id", ignore = true)
    @Mapping(target = "author", ignore = true)
    @Mapping(target = "ad", ignore = true)
    @Mapping(target = "createdAt", ignore = true)
    CommentEntity toEntity(CreateOrUpdateComment createOrUpdateComment);

    default CommentEntity toEntityWithRelations(CreateOrUpdateComment dto, UserEntity author, AdEntity ad) {
        CommentEntity entity = toEntity(dto);
        entity.setAuthor(author);
        entity.setAd(ad);
        entity.setCreatedAt(System.currentTimeMillis());
        return entity;
    }

    @Mapping(target = "id", ignore = true)
    @Mapping(target = "author", ignore = true)
    @Mapping(target = "ad", ignore = true)
    @Mapping(target = "createdAt", ignore = true)
    void updateCommentFromDto(CreateOrUpdateComment dto, @MappingTarget CommentEntity entity);
}