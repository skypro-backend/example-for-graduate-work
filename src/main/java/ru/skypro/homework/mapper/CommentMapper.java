package ru.skypro.homework.mapper;

import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.MappingTarget;
import ru.skypro.homework.dto.CommentDto;
import ru.skypro.homework.dto.CreateOrUpdateCommentDto;
import ru.skypro.homework.model.Comment;

import java.time.LocalDateTime;
import java.time.ZoneId;
import java.time.ZonedDateTime;
import java.util.List;

@Mapper(componentModel = "spring")
public interface CommentMapper {
    ZoneId DEFAULT_ZONE = ZoneId.systemDefault();

    static Long toLong(LocalDateTime time) {
        if (time == null) {
            return null;
        }
        return ZonedDateTime.of(time, DEFAULT_ZONE).toInstant().toEpochMilli();
    }

    @Mapping(source = "id", target = "pk")
    @Mapping(source = "author.id", target = "author")
    @Mapping(source = "author.image", target = "authorImage")
    @Mapping(source = "author.firstName", target = "authorFirstName")
    CommentDto commentToCommentDto(Comment comment);

    Comment commentDtoToComment(CreateOrUpdateCommentDto createOrUpdateCommentDto);

    void updateCommentFromDto(CreateOrUpdateCommentDto createOrUpdateCommentDto, @MappingTarget Comment comment);

    List<CommentDto> commentsToCommentDtos(List<Comment> comments);

}
