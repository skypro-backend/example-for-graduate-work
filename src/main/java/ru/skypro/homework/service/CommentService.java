package ru.skypro.homework.service;

import org.springframework.stereotype.Service;
import ru.skypro.homework.dto.CommentDTO;
import ru.skypro.homework.model.Comment;
import ru.skypro.homework.repository.CommentRepository;

@Service
public class CommentService {
    private final CommentRepository commentRepository;

    public CommentService(CommentRepository commentRepository) {
        this.commentRepository = commentRepository;
    }

    public void createComment(CommentDTO commentDTO) {
        Comment comments = new Comment();
        comments.setAuthor(commentDTO.getAuthor());
        comments.setAuthorImage(commentDTO.getAuthorImage());
        comments.setAuthorFirstName(commentDTO.getAuthorFirstName());
        comments.setCreatedAt(commentDTO.getCreatedAt());
        comments.setPk(commentDTO.getPk());
        comments.setText(commentDTO.getText());
        commentRepository.save(comments);

    }
}
