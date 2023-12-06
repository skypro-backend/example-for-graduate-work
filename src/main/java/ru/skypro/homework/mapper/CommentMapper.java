package ru.skypro.homework.mapper;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;
import ru.skypro.homework.dto.CommentDTO;
import ru.skypro.homework.dto.Comments;
import ru.skypro.homework.dto.CreateOrUpdateComment;
import ru.skypro.homework.model.Ad;
import ru.skypro.homework.model.Comment;
import ru.skypro.homework.model.User;
import ru.skypro.homework.repository.CommentRepository;
import ru.skypro.homework.repository.ImageRepository;
import ru.skypro.homework.repository.UserRepository;

import java.time.Instant;
import java.time.LocalDateTime;
import java.time.ZoneId;
import java.time.ZonedDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.TimeZone;

@Component
@RequiredArgsConstructor
public class CommentMapper {

    private final CommentRepository commentRepository;

    public CommentDTO mapToDTO(Comment comment) {
        ZonedDateTime zonedDateTime = ZonedDateTime.of(comment.getCreatedAt(), ZoneId.systemDefault());
        return new CommentDTO(
            comment.getAuthor().getId(),
                "/image/" + comment.getAuthorImage().getId(),
            comment.getAuthorFirstName(),
            zonedDateTime.toInstant().toEpochMilli(),
            comment.getPk(),
            comment.getText()
        );
    }

    public Comments mapToListOfDTO(Ad ad) {
        List<CommentDTO> results = new ArrayList<>();
        for (int i = 0; i < ad.getComments().size(); i++) {
            results.add(mapToDTO(ad.getComments().get(i)));
        }
        return new Comments(ad.getComments().size(), results);
    }

    public Comment createFromCreateOrUpdate(CreateOrUpdateComment createOrUpdateComment, User author, Ad ad) {
        LocalDateTime localDateTime = LocalDateTime.now();
        Comment comment = new Comment();
        comment.setAuthor(author);
        comment.setAuthorImage(author.getImage());
        comment.setAuthorFirstName(author.getFirstName());
        comment.setCreatedAt(localDateTime);
        comment.setText(createOrUpdateComment.getText());
        comment.setAd(ad);
        return commentRepository.save(comment);
    }

    public Comment updateFromCreateOrUpdate(Comment comment, CreateOrUpdateComment createOrUpdateComment) {
        LocalDateTime localDateTime = LocalDateTime.now();
        comment.setCreatedAt(localDateTime);
        comment.setText(createOrUpdateComment.getText());
        return commentRepository.save(comment);
    }

}
