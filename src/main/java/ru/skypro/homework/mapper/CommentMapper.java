package ru.skypro.homework.mapper;

import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.MappingTarget;
import ru.skypro.homework.dto.Comment;
import ru.skypro.homework.dto.CreateOrUpdateComment;

@Mapper(
        componentModel = "spring",
        uses = IntegerToUserMapper.class
)
public interface CommentMapper {

    @Mapping(source = "id", target = "pk")
    @Mapping(source = "author.image", target = "authorImage")
    @Mapping(source = "author.firstName", target = "authorFirstName")
    Comment toDto(
            ru.skypro.homework.entity.Comment comment
    );

    @Mapping(target = "id", ignore = true)
    @Mapping(target = "author", ignore = true)
    @Mapping(target = "ad", ignore = true)
    @Mapping(target = "createdAt", ignore = true)
    ru.skypro.homework.entity.Comment toEntity(
            CreateOrUpdateComment comment
    );

    void updateCommentFromDto(
            CreateOrUpdateComment dto,
            @MappingTarget ru.skypro.homework.entity.Comment comment
    );
}