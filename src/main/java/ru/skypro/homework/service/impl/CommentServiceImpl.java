package ru.skypro.homework.service.impl;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import ru.skypro.homework.dto.*;
import ru.skypro.homework.mapper.CommentMapper;
import ru.skypro.homework.model.Ad;
import ru.skypro.homework.model.Comment;
import ru.skypro.homework.model.User;
import ru.skypro.homework.repository.AdRepository;
import ru.skypro.homework.repository.CommentRepository;
import ru.skypro.homework.repository.UserRepository;
import ru.skypro.homework.service.CommentService;
import java.util.List;

/**
 * Реализация сервиса для работы с комментариями {@link ru.skypro.homework.service.CommentService}.
 * Обеспечивает логику привязки комментариев к объявлениям, фиксацию времени создания в миллисекундах
 * и проверку прав доступа перед удалением или редактированием текста.
 */
@Slf4j
@Service
@RequiredArgsConstructor
@Transactional
public class CommentServiceImpl implements CommentService {

    private final CommentRepository commentRepository;
    private final AdRepository adRepository;
    private final UserRepository userRepository;
    private final CommentMapper commentMapper;

    /** {@inheritDoc} */
    @Override
    @Transactional(readOnly = true)
    public Comments getComments(Integer adId) {
        List<Comment> comments = commentRepository.findAllByAdId(adId);
        Comments commentsDto = new Comments();
        List<CommentDto> list = commentMapper.toCommentDtoList(comments);
        commentsDto.setCount(list.size());
        commentsDto.setResults(list);
        return commentsDto;
    }

    /** {@inheritDoc} */
    @Override
    public CommentDto addComment(Integer adId, CreateOrUpdateComment properties, String username) {
        User author = userRepository.findByEmail(username)
                .orElseThrow(() -> new IllegalArgumentException("User not found"));
        Ad ad = adRepository.findById(adId)
                .orElseThrow(() -> new IllegalArgumentException("Ad not found"));

        Comment comment = commentMapper.toEntity(properties);
        comment.setAuthor(author);
        comment.setAd(ad);
        comment.setCreatedAt(System.currentTimeMillis());

        Comment savedComment = commentRepository.save(comment);
        log.info("Comment added to ad id: {} by user: {}", adId, username);
        return commentMapper.toCommentDto(savedComment);
    }

    /** {@inheritDoc} */
    @Override
    public void deleteComment(Integer adId, Integer commentId) {
        commentRepository.deleteById(commentId);
        log.info("Comment deleted with id: {}", commentId);
    }

    /** {@inheritDoc} */
    @Override
    public CommentDto updateComment(Integer adId, Integer commentId, CreateOrUpdateComment properties) {
        Comment comment = commentRepository.findById(commentId)
                .orElseThrow(() -> new IllegalArgumentException("Comment not found"));
        comment.setText(properties.getText());
        Comment savedComment = commentRepository.save(comment);
        log.info("Comment updated with id: {}", commentId);
        return commentMapper.toCommentDto(savedComment);
    }

    /**
     * Проверяет, является ли текущий авторизованный пользователь автором комментария
     * или он обладает правами администратора (ROLE_ADMIN).
     * <p>
     * Данный метод используется инфраструктурой Spring Security через SpEL-выражения
     * в аннотациях {@code @PreAuthorize} на уровне контроллера.
     *
     * @param commentId уникальный идентификатор проверяемого комментария
     * @return {@code true}, если текущий пользователь имеет право изменить/удалить комментарий
     */
    @Transactional(readOnly = true)
    public boolean isAuthorOrAdmin(Integer commentId) {
        String currentUsername = SecurityContextHolder.getContext().getAuthentication().getName();
        Comment comment = commentRepository.findById(commentId).orElse(null);
        if (comment == null) return false;

        boolean isAdmin = SecurityContextHolder.getContext().getAuthentication().getAuthorities().stream()
                .anyMatch(a -> a.getAuthority().equals("ROLE_ADMIN"));

        return isAdmin || comment.getAuthor().getEmail().equals(currentUsername);
    }
}