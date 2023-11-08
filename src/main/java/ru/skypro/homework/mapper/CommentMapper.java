package ru.skypro.homework.mapper;

import org.springframework.stereotype.Component;
import ru.skypro.homework.dto.*;
import ru.skypro.homework.entity.*;

import java.time.LocalDateTime;
import java.time.ZoneOffset;
import java.util.List;

@Component
public abstract class CommentMapper {
    public CommentDto entityToCommentDto(Comment entity) {
        return new CommentDto (entity.getAuthor().getId(), entity.getAuthor().getImagePath(),
                entity.getAuthor().getFirstName(), getMillis(entity.getCreatedAt()),
                entity.getPk(), entity.getText());
    }



    public Comment commentToEntity(CreateOrUpdateCommentDto createOrUpdateCommentDto, Ad ad, User author) {
        return new Comment((org.apache.catalina.User) author, LocalDateTime.now(), createOrUpdateCommentDto.getText(), ad);
    }

    private long getMillis(LocalDateTime time) {
        return time.toInstant(ZoneOffset.ofHours(5)).toEpochMilli();
    }

    public abstract CommentDto toCommentDto(Comment comment);

    public abstract List<CommentDto> toCommentsDto(List<Comment> comments);

    public abstract CommentDto CommentToCommentDto(Comment comment);

    public abstract Comment toCommentFromCreateOrUpdateComment(CreateOrUpdateCommentDto createOrUpdateCommentDto);

    public abstract Comment toCommenFromCreateOrUpdateComment(CreateOrUpdateCommentDto createOrUpdateCommentDto);

    public abstract String authorImageToString(User user);
}