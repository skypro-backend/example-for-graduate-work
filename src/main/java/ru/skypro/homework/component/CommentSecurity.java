package ru.skypro.homework.component;

import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Component;
import org.springframework.web.server.ResponseStatusException;
import ru.skypro.homework.entity.CommentEntity;
import ru.skypro.homework.repository.CommentRepository;

/**
 * Компонент для проверки доступа к комментариям.
 * Позволяет определить, имеет ли текущий пользователь права на выполнение операций с комментариями,
 * основываясь на том, является ли он автором комментария или администратором.
 */
@Component("commentSecurity")
@RequiredArgsConstructor
public class CommentSecurity {

    private final CommentRepository commentRepository;

    /**
     * Проверяет, имеет ли текущий пользователь доступ к комментарию по его ID.
     * Пользователь имеет доступ, если он автор комментария или обладает ролью администратора.
     *
     * @param commentId Идентификатор комментария, к которому проверяется доступ.
     * @return true, если пользователь имеет доступ, иначе false.
     * @throws ResponseStatusException если комментарий с указанным ID не найден (HTTP статус 404).
     */
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
