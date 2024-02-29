package ru.skypro.sitesforresaleofthings.mapper;

import org.springframework.stereotype.Service;
import ru.skypro.sitesforresaleofthings.dto.CommentDTO;
import ru.skypro.sitesforresaleofthings.dto.CreateOrUpdateCommentDTO;
import ru.skypro.sitesforresaleofthings.entity.Ad;
import ru.skypro.sitesforresaleofthings.entity.Comment;
import ru.skypro.sitesforresaleofthings.entity.User;

import java.time.Instant;
import java.time.LocalDateTime;
import java.util.TimeZone;

/**
 * Создаем сервис(маппер), который устанавливает соответствие(маппит) из сущности в DTO и обратно
 */
@Service
public class CommentMapper {

    public CommentDTO toDTO(Comment comment) {

        TimeZone timeZone = TimeZone.getDefault();
        LocalDateTime localDateTime = LocalDateTime.ofInstant(comment.getCreatedAt(), timeZone.toZoneId());

        CommentDTO commentDTO = new CommentDTO();
        commentDTO.setAuthor(comment.getAuthor().getId());
        commentDTO.setAuthorFirstName(comment.getAuthor().getFirstName());
        commentDTO.setPk(comment.getPk());
        commentDTO.setAuthorImage(comment.getAuthor().getImage());
        commentDTO.setCreatedAt(localDateTime);
        commentDTO.setText(comment.getText());
        if (comment.getAuthor().getImage() != null) {
            commentDTO.setAuthorImage(String.format("/ads/image/%s", comment.getAuthor().getImage()));
        } else {
            commentDTO.setAuthorImage(null);
        }
        return commentDTO;
    }

    public Comment toEntity(CreateOrUpdateCommentDTO createOrUpdateCommentDTO, User author, Ad ad) {
        Comment comment = new Comment();
        comment.setText(createOrUpdateCommentDTO.getText());
        comment.setAd(ad);
        comment.setAuthor(author);
        comment.setCreatedAt(Instant.now());
        return comment;
    }
}