package ru.skypro.homework.component;

import org.springframework.http.HttpStatus;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Component;
import org.springframework.web.server.ResponseStatusException;
import ru.skypro.homework.entity.CommentEntity;
import ru.skypro.homework.repository.CommentRepository;

@Component("commentSecurity")
public class CommentSecurity {

    private final CommentRepository commentRepository;

    public CommentSecurity(CommentRepository commentRepository) {
        this.commentRepository = commentRepository;
    }

    public boolean hasAccess(Integer commentId) {
        String username = SecurityContextHolder.getContext().getAuthentication().getName();

        CommentEntity comment = commentRepository.findById(commentId)
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Комментарий не найден"));

        boolean isAuthor = comment.getAuthor().getEmail().equals(username);
        boolean isAdmin = SecurityContextHolder.getContext().getAuthentication().getAuthorities().stream()
                .anyMatch(a -> a.getAuthority().equals("ROLE_ADMIN"));

        return isAuthor || isAdmin;
    }
}
