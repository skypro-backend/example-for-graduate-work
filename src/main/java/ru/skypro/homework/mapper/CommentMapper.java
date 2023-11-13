package ru.skypro.homework.mapper;

import org.springframework.stereotype.Component;
import ru.skypro.homework.dto.CommentDto;
import ru.skypro.homework.dto.CreateOrUpdateCommentDto;
import ru.skypro.homework.entity.Ad;
import ru.skypro.homework.entity.Comment;
import ru.skypro.homework.entity.User;
import java.time.LocalDateTime;
import java.time.ZoneOffset;
@Component
public class CommentMapper {
    public CommentDto entityToCommentDto(Comment entity) {
        return new CommentDto (entity.getAuthor().getId(), entity.getAuthor().getImagePath(),
                entity.getAuthor().getFirstName(), getMillis(entity.getCreatedAt()),
                entity.getPk(), entity.getText());
    }

    public Comment createСommentToEntity(CreateOrUpdateCommentDto createOrUpdateCommentDto, Ad ad, User author) {
        return new Comment (author, LocalDateTime.now(), createOrUpdateCommentDto.getText(), ad);
    }

    private long getMillis(LocalDateTime time) {
        return time.toInstant(ZoneOffset.ofHours(5)).toEpochMilli();
    }


}