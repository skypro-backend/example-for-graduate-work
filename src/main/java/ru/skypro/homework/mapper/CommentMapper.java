package ru.skypro.homework.mapper;

import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.MappingTarget;
import org.mapstruct.ReportingPolicy;
import ru.skypro.homework.dto.Comment;
import ru.skypro.homework.dto.CreateOrUpdateComment;
import ru.skypro.homework.entity.AdEntity;
import ru.skypro.homework.entity.CommentEntity;
import ru.skypro.homework.entity.UserEntity;

import java.time.LocalDateTime;
import java.time.ZoneOffset;

@Mapper(componentModel = "spring", unmappedTargetPolicy = ReportingPolicy.IGNORE)
public interface CommentMapper {

    @Mapping(source = "author.id", target = "author")
    @Mapping(source = "author.image", target = "authorImage")
    @Mapping(source = "author.firstName", target = "authorFirstName")
    @Mapping(source = "createdAt", target = "createdAt", qualifiedByName = "localDateTimeToLong")
    Comment toDto(CommentEntity commentEntity);

    @Mapping(target = "id", ignore = true)
    @Mapping(target = "author", ignore = true)
    @Mapping(target = "ad", ignore = true)
    @Mapping(target = "createdAt", expression = "java(java.time.LocalDateTime.now())")
    CommentEntity toEntity(CreateOrUpdateComment createOrUpdateComment);

    @Mapping(target = "id", ignore = true)
    @Mapping(target = "author", ignore = true)
    @Mapping(target = "ad", ignore = true)
    @Mapping(target = "createdAt", ignore = true)
    CommentEntity updateCommentEntity(CreateOrUpdateComment createOrUpdateComment, @MappingTarget CommentEntity commentEntity);

    default Long localDateTimeToLong(LocalDateTime localDateTime) {
        return localDateTime.toEpochSecond(ZoneOffset.UTC) * 1000;
    }

    default CommentEntity createCommentEntity(CreateOrUpdateComment createOrUpdateComment,
                                              UserEntity author,
                                              AdEntity ad) {
        CommentEntity commentEntity = toEntity(createOrUpdateComment);
        commentEntity.setAuthor(author);
        commentEntity.setAd(ad);
        return commentEntity;
    }
}
