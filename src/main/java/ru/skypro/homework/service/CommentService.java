package ru.skypro.homework.service;

import jakarta.persistence.EntityNotFoundException;
import jakarta.transaction.Transactional;
import org.springframework.security.access.AccessDeniedException;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;
import ru.skypro.homework.dto.CommentDTO;
import ru.skypro.homework.dto.CreateOrUpdateCommentDTO;
import ru.skypro.homework.mapper.CommentMapper;
import ru.skypro.homework.model.Ad;
import ru.skypro.homework.model.Comment;
import ru.skypro.homework.model.Users;
import ru.skypro.homework.repository.CommentRepository;

@Service
@Transactional

public class CommentService {

    private final CommentRepository commentRepository;
    private final CommentMapper commentMapper;

    public CommentService(CommentRepository commentRepository, CommentMapper commentMapper) {
        this.commentRepository = commentRepository;
        this.commentMapper = commentMapper;
    }

    public CommentDTO createComment(CreateOrUpdateCommentDTO commentDTO, Ad ad, Users author) {
        Comment commentEntity = commentMapper.toEntity(commentDTO);
        commentEntity.setAd(ad);
        commentEntity.setAuthor(author);

        Comment savedComment = commentRepository.save(commentEntity);
        return commentMapper.toDTO(savedComment);
    }

    public CommentDTO updateComment(Long id, CreateOrUpdateCommentDTO commentDTO, Users currentUser) {
        Comment existingComment = commentRepository.findById(Math.toIntExact(id))
                .orElseThrow(() -> new EntityNotFoundException("Comment not found"));

        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        String currentUsername = authentication.getName();

        // Проверка, что текущий пользователь — автор комментария
        if (!existingComment.getAuthor().getUsername().equals(currentUsername)) {
            throw new AccessDeniedException("You can only edit your own comments");
        }

        commentMapper.updateEntityFromDTO(commentDTO, existingComment);
        Comment updatedComment = commentRepository.save(existingComment);

        return commentMapper.toDTO(updatedComment);
    }

    public void deleteComment(Long id, Users currentUser) {
        Comment comment = commentRepository.findById(Math.toIntExact(id))
                .orElseThrow(() -> new EntityNotFoundException("Comment not found"));

        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        String currentUsername = authentication.getName();

        if (!comment.getAuthor().getUsername().equals(currentUsername)) {
            throw new AccessDeniedException("You can only delete your own comments");
        }

        commentRepository.delete(comment);
    }
}
