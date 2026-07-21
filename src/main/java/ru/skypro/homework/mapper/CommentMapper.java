package ru.skypro.homework.mapper;

import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import ru.skypro.homework.dto.Comment;

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

    @Mapping(source = "pk", target = "id")
    ru.skypro.homework.entity.Comment toEntity(
            Comment comment
    );

}