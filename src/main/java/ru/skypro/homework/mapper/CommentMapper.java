package ru.skypro.homework.mapper;

import org.springframework.stereotype.Component;
import ru.skypro.homework.dto.Comment;
import ru.skypro.homework.dto.Comments;
import ru.skypro.homework.repository.ImageRepository;
import ru.skypro.homework.repository.UserRepository;

import java.time.Instant;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.TimeZone;

@Component
public class CommentMapper {
    private final UserRepository userRepository;
    private final ImageRepository imageRepository;
    private final AdRepository adRepository;

    public CommentMapper(
                       UserRepository userRepository,
                       ImageRepository imageRepository) {
        this.userRepository = userRepository;
        this.imageRepository = imageRepository;
    }
    public Comment mapEntityToDTO(ru.skypro.homework.model.Comment comment) {
        return CommentDTO(
                comment.getUser().getId(),
                comment.getImage().getLink(),
                comment.getAuthorFirstName(),
                comment.getCreatedAt().toEpochMilli(),
                comment.getPk(),
                comment.getText()
        );
    }

    public ru.skypro.homework.model.Comment mapDTOToEntity(Comment comment) {
        return Comment(
                userRepository.findById(comment.getAuthor()),
                imageRepository.findByLink(comment.getAuthorImage()),
                comment.getAuthorFirstName(),
                LocalDateTime.ofInstant(Instant.ofEpochMilli(comment.getCreatedAt()),
                        TimeZone.getDefault().toZoneId()),
                comment.getPk(),
                comment.getText()
        );
    }

    public Comments mapCommentsToDTO (Ad ad) {
        List<Comment> results = new ArrayList<>();
        for (int i = 0; i < ad.getComments().size(); i++) {
            results.add(ad.getComments().get(i).mapEntityToDTO);
        }
        return Comments(
                ad.getComments().size(),
                results
        )
    }

}
