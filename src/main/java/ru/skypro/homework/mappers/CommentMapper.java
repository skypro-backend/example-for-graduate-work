package ru.skypro.homework.mappers;

import org.mapstruct.*;
import ru.skypro.homework.dto.comments.out.CommentDto;
import ru.skypro.homework.dto.comments.out.CommentsDto;
import ru.skypro.homework.entity.comments.Comment;

import java.time.LocalDateTime;
import java.time.ZoneId;
import java.util.List;

@Mapper(componentModel = "spring", uses = CustomUserMapper.class)
public interface CommentMapper {

    @Mappings({
            @Mapping(target = "author", source = "author.id"),
            @Mapping(target = "authorImage", source = "author.image"),
            @Mapping(target = "authorFirstName", source = "author.firstName")

    })
    CommentDto toCommentDto(Comment comment);

    default Long mapDateToLong(LocalDateTime createdAt) {
        return createdAt.atZone(ZoneId.systemDefault()).toInstant().toEpochMilli();
    }

    List<CommentDto> toCommentDtoList(List<Comment> comments);

    default CommentsDto toCommentsDto(List<Comment> comments) {
        if (comments == null) {
            return null;
        }
        CommentsDto commentsDto = new CommentsDto();
        commentsDto.setResults(toCommentDtoList(comments));
        commentsDto.setCount(comments.size());
        return commentsDto;
    }

}