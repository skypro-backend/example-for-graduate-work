package ru.skypro.homework.mapper;

import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import ru.skypro.homework.api.dto.Comment;
import ru.skypro.homework.api.dto.Comments;
import ru.skypro.homework.api.dto.CreateOrUpdateComment;
import ru.skypro.homework.db.model.CommentModel;

import java.time.LocalDateTime;
import java.time.ZoneId;
import java.util.ArrayList;
import java.util.List;

@Mapper(componentModel = "spring")
public interface CommentMapper {

    @Mapping(target = "pk", source = "id")
    @Mapping(target = "author", source = "author.id")
    @Mapping(target = "authorImage", source = "author.image")
    @Mapping(target = "authorFirstName", source = "author.firstName")
    Comment toDto(CommentModel model);

    @Mapping(target = "id", ignore = true)
    @Mapping(target = "ad", ignore = true)
    @Mapping(target = "author", ignore = true)
    @Mapping(target = "createdAt", ignore = true)
    CommentModel toModel(CreateOrUpdateComment dto);

    List<Comment> toDtoList(List<CommentModel> models);

    default Comments toComments(List<CommentModel> models) {
        List<Comment> results = models == null ? new ArrayList<>() : toDtoList(models);
        Comments comments = new Comments();
        comments.setCount(results.size());
        comments.setResults(results);
        return comments;
    }

    default Long toMillis(LocalDateTime createdAt) {
        return createdAt == null ? null : createdAt.atZone(ZoneId.systemDefault()).toInstant().toEpochMilli();
    }
}
