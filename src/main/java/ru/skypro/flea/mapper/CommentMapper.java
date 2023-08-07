package ru.skypro.flea.mapper;

import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.MappingTarget;
import ru.skypro.flea.dto.CommentDto;
import ru.skypro.flea.dto.CommentsDto;
import ru.skypro.flea.dto.CreateOrUpdateCommentDto;
import ru.skypro.flea.model.Comment;

import java.util.Collection;
import java.util.List;

@Mapper
public interface CommentMapper {

    @Mapping(target = "author", source = "user.id")
    @Mapping(target = "authorImage", source = "user.image")
    @Mapping(target = "authorFirstName", source = "user.firstName")
    @Mapping(target = "createdAt",
            expression = "java(entity.getPublicDate()" +
                    ".toInstant(java.time.OffsetDateTime.now().getOffset())" +
                    ".toEpochMilli())")
    @Mapping(target = "pk", source = "id")
    @Mapping(target = "text", source = "title")
    CommentDto toCommentDto(Comment entity);

    List<CommentDto> toCommentDtoList(Collection<Comment> comments);

    default CommentsDto toCommentsDto(Collection<Comment> comments) {
        CommentsDto dto = new CommentsDto();
        dto.setResults(toCommentDtoList(comments));
        dto.setCount(comments.size());

        return dto;
    }

    @Mapping(target = "title", source = "text")
    Comment createCommentFromDto(CreateOrUpdateCommentDto dto);

    @Mapping(target = "title", source = "text")
    void updateCommentFromDto(@MappingTarget Comment comment, CreateOrUpdateCommentDto dto);

}
