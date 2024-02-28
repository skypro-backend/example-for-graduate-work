package ru.skypro.homework.mapper;

import org.springframework.stereotype.Service;
import ru.skypro.homework.dto.CommentDTO;
import ru.skypro.homework.dto.Comments;
import ru.skypro.homework.model.Comment;
import ru.skypro.homework.model.Image;
import ru.skypro.homework.model.User;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class CommentMapper {
    public CommentDTO toCommentDTO(Comment comment) {
        User author = comment.getAuthor();
        Image image = author != null ? author.getImage() : null;
        return new CommentDTO(
                comment.getId(),
                author != null ? author.getId() : null,
                image != null ? "images/" + image.getId() : null,
                author != null ? author.getFirstName() : null,
                comment.getCreatedAt(),
                comment.getText()
        );
    }

    public Comments toComments(List<Comment> comments) {
        return new Comments(
                (long) comments.size(),
                comments.stream()
                        .map(this::toCommentDTO)
                        .collect(Collectors.toList())
        );
    }
}
