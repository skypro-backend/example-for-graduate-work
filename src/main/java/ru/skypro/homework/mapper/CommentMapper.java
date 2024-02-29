package ru.skypro.homework.mapper;

import org.springframework.stereotype.Service;
import ru.skypro.homework.dto.CommentDTO;
import ru.skypro.homework.model.Comment;
import ru.skypro.homework.model.User;

@Service
public class CommentMapper {
    public CommentDTO toCommentDTO(Comment comment) {
        User author = comment.getAuthor();
        return new CommentDTO(
                comment.getId(),
                author != null ? author.getId() : null,
                author != null ? author.getImage() : null,
                author != null ? author.getFirstName() : null,
                comment.getCreatedAt(),
                comment.getText()
        );
    }
}
