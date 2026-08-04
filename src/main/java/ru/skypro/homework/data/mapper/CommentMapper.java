package ru.skypro.homework.data.mapper;

import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.Named;
import ru.skypro.homework.data.dto.comment.CommentItemDto;
import ru.skypro.homework.data.entity.CommentEntity;

import java.time.Instant;
import java.util.List;

import static org.mapstruct.MappingConstants.ComponentModel.SPRING;

@Mapper(componentModel = SPRING)
public interface CommentMapper {

    @Mapping(target = "pk", source = "id")
    @Mapping(target = "author", source = "profile.id")
    @Mapping(target = "authorFirstName", source = "profile.firstName")
    @Mapping(target = "authorImage", source = "profile.avatarS3Key")
    @Mapping(target = "createdAt", source = "createdAt", qualifiedByName = "instantToLong")
    CommentItemDto toCommentItemDto(CommentEntity entity);

    List<CommentItemDto> toCommentItemDtoList(List<CommentEntity> entities);

    @Named("instantToLong")
    default Long instantToLong(Instant instant) {
        return instant != null ? instant.toEpochMilli() : null;
    }
}
