package ru.skypro.homework.mapper;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;
import ru.skypro.homework.dto.Comments;
import ru.skypro.homework.model.Ad;
import ru.skypro.homework.model.Comment;
import ru.skypro.homework.repository.AdRepository;
import ru.skypro.homework.repository.CommentRepository;
import ru.skypro.homework.repository.ImageRepository;
import ru.skypro.homework.repository.UserRepository;

import java.time.Instant;
import java.time.LocalDateTime;
import java.time.ZoneId;
import java.time.ZonedDateTime;
import java.time.temporal.ChronoField;
import java.util.ArrayList;
import java.util.List;
import java.util.TimeZone;

@Component
@RequiredArgsConstructor
public class CommentMapper {

    private final UserRepository userRepository;
    private final ImageRepository imageRepository;
    private final CommentRepository commentRepository;

    public ru.skypro.homework.dto.Comment mapToDTO(Comment comment) {
        ZonedDateTime zonedDateTime = ZonedDateTime.of(comment.getCreatedAt(), ZoneId.systemDefault());
        return new ru.skypro.homework.dto.Comment(
            comment.getAuthor().getId(),
            comment.getAuthorImage().getLink(),
            comment.getAuthorFirstName(),
            zonedDateTime.toEpochSecond(),
            comment.getPk(),
            comment.getText()
        );
    }

    public Comment mapToEntity(ru.skypro.homework.dto.Comment commentDTO) {
        return new Comment(
                userRepository.findById(commentDTO.getAuthor()).get(),
                imageRepository.findByLink(commentDTO.getAuthorImage()),
                commentDTO.getAuthorFirstName(),
                LocalDateTime.ofInstant(Instant.ofEpochSecond(commentDTO.getCreatedAt()), TimeZone
                        .getDefault().toZoneId()),
                commentDTO.getPk(),
                commentDTO.getText(),
                commentRepository.findByPk(commentDTO.getPk()).getAd()
        );
    }

    public Comments mapToListOfDTO(Ad ad) {
        List<ru.skypro.homework.dto.Comment> results = new ArrayList<>();
        for (int i = 0; i < ad.getComments().size(); i++) {
            results.add(mapToDTO(ad.getComments().get(i)));
        }
        return new Comments(ad.getComments().size(), results);
    }

    public List<Comment> mapBackToListOfEntities(Comments comments) {
        List<Comment> results = new ArrayList<>();
        for (int i = 0; i < comments.getResults().size(); i++) {
            results.add(mapToEntity(comments.getResults().get(i)));
        }
        return results;
    }

}
