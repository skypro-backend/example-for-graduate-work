package ru.skypro.homework.service.impl;

import lombok.RequiredArgsConstructor;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import ru.skypro.homework.dto.request.CommentRequestDto;
import ru.skypro.homework.dto.response.CommentResponseDto;
import ru.skypro.homework.dto.response.CommentsResponseDto;
import ru.skypro.homework.entity.Ad;
import ru.skypro.homework.entity.Comment;
import ru.skypro.homework.entity.User;
import ru.skypro.homework.exception.NotFoundException;
import ru.skypro.homework.mapper.CommentMapper;
import ru.skypro.homework.repository.AdRepository;
import ru.skypro.homework.repository.CommentRepository;
import ru.skypro.homework.service.CommentService;
import ru.skypro.homework.service.CurrentUserService;

import java.util.List;

/**
 * Сервис управления комментариями.
 * Обеспечивает:
 * - получение комментариев объявления,
 * - создание комментариев,
 * - редактирование и удаление комментариев.
 * Поддерживает проверку владельца и роли ADMIN.
 */
@Service("commentService")
@RequiredArgsConstructor
@Transactional
public class CommentServiceImpl implements CommentService {
    private final CommentRepository commentRepository;
    private final CommentMapper commentMapper;
    private final AdRepository adRepository;
    private final CurrentUserService currentUserService;

    @Override
    @Transactional(readOnly = true)
    public CommentsResponseDto getComments(Integer adId) {
        getAdOrThrow(adId);
        List<Comment> comments = commentRepository.findByAdId(adId);
        return commentMapper.toCommentsResponseDto(comments);
    }

    /**
     * Добавление комментария.
     */
    @Override
    public CommentResponseDto addComment(Integer adId, CommentRequestDto dto) {
        User currentUser = currentUserService.getCurrentUser();
        Ad ad = getAdOrThrow(adId);
        Comment comment = commentMapper.commentRequestDtoToComment(dto);
        comment.setAuthor(currentUser);
        comment.setAd(ad);
        comment.setCreatedAt(System.currentTimeMillis());
        commentRepository.save(comment);
        return commentMapper.commentToDto(comment);
    }

    private Ad getAdOrThrow(Integer adId) {
        return adRepository.findById(adId).orElseThrow(() -> new NotFoundException("Ad not found"));
    }

    /**
     * Обновление комментария.
     * Доступно владельцу или администратору.
     */
    @PreAuthorize("hasRole('ADMIN') or @commentService.isCommentOwner(#commentId, authentication)")
    @Override
    public CommentResponseDto updateComment(Integer adId, Integer commentId, CommentRequestDto dto) {
        Comment comment = getCommentOrThrow(commentId);
        if (!comment.getAd().getId().equals(adId)) {
            throw new NotFoundException("Comment doesn't belong to this ad");
        }
        comment.setText(dto.getText());
        Comment updatedComment = commentRepository.save(comment);
        return commentMapper.commentToDto(updatedComment);
    }

    private Comment getCommentOrThrow(Integer commentId) {
        return commentRepository.findById(commentId).orElseThrow(() -> new NotFoundException("Comment not found"));
    }

    /**
     * Удаление комментария.
     * Доступно владельцу или администратору.
     */
    @PreAuthorize("hasRole('ADMIN') or @commentService.isCommentOwner(#commentId, authentication)")
    @Override
    public void deleteComment(Integer adId, Integer commentId) {
        Comment comment = getCommentOrThrow(commentId);
        if (!comment.getAd().getId().equals(adId)) {
            throw new NotFoundException("Comment doesn't belong to this ad");
        }
        commentRepository.delete(comment);
    }

    /**
     * Проверяет, является ли пользователь владельцем комментария.
     * @param commentId идентификатор объявления
     * @param authentication текущая аутентификация
     * @return true, если пользователь является автором комментария
     */
    public boolean isCommentOwner(Integer commentId, Authentication authentication) {
        return commentRepository.existsByIdAndAuthorUsername(commentId, authentication.getName());
    }
}
