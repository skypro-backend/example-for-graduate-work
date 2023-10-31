package ru.skypro.homework.mapper;

import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import ru.skypro.homework.dto.CommentDto;
import ru.skypro.homework.dto.CommentsDto;
import ru.skypro.homework.dto.CreateOrUpdateCommentDto;
import ru.skypro.homework.entity.Ad;
import ru.skypro.homework.entity.Comment;
import ru.skypro.homework.entity.User;

import java.time.LocalDateTime;
import java.time.ZoneOffset;
import java.util.List;

@Mapper
public interface CommentMapper {

    @Mapping(target = "author", source = "user.id")
    @Mapping(target = "authorImage", source = "user.userImage.filePath")
    @Mapping(target = "authorFirstName", source = "user.firstName")
    @Mapping(target = "createdAt", expression = "java(getLongFromLocalDateTime(comment.getCreatedAt()))")
    @Mapping(target = "pk", source = "id")
    CommentDto entityToCommentDto(Comment comment);

    @Mapping(target = "id", ignore = true)
    @Mapping(target = "createdAt", ignore = true)
    @Mapping(target = "user", source = "user")
    @Mapping(target = "ad", source = "ad")
    Comment createCommentDtoToEntity(Ad ad, CreateOrUpdateCommentDto createOrUpdateCommentDto, User user);

    @Mapping(target = "id", source = "commentId")
    @Mapping(target = "user", source = "user")
    @Mapping(target = "ad", source = "ad")
    Comment updateCommentDtoToEntity(Ad ad, Integer commentId,
                                                     CreateOrUpdateCommentDto createOrUpdateCommentDto,
                                                     LocalDateTime createdAt, User user);

    default CommentsDto commentDtoListToCommentsDto(List<CommentDto> commentDtoList) {
        return new CommentsDto(commentDtoList.size(), commentDtoList);
    }

    default Long getLongFromLocalDateTime(LocalDateTime localDateTime) {
        return localDateTime.toInstant(ZoneOffset.UTC).toEpochMilli();
    }

}
