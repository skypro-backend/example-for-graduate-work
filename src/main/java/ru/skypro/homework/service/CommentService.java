package ru.skypro.homework.service;

import org.springframework.security.access.AccessDeniedException;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;
import ru.skypro.homework.dto.comment.Comment;
import ru.skypro.homework.dto.comment.CreateOrUpdateComment;
import ru.skypro.homework.entity.AdEntity;
import ru.skypro.homework.entity.CommentEntity;
import ru.skypro.homework.entity.UserEntity;
import ru.skypro.homework.mapper.CommentMapper;
import ru.skypro.homework.repository.AdRepository;
import ru.skypro.homework.repository.CommentRepository;
import ru.skypro.homework.repository.UserRepository;

import java.util.List;
import java.util.stream.Collectors;

/**
 * Сервис для работы с комментариями.
 * Обеспечивает получение, добавление и удаление комментариев к объявлениям.
 */
@Service
public class CommentService {

    private final CommentRepository commentRepository;
    private final AdRepository adRepository;
    private final UserRepository userRepository;
    private final CommentMapper commentMapper;

    public CommentService(CommentRepository commentRepository,
                          AdRepository adRepository,
                          UserRepository userRepository,
                          CommentMapper commentMapper) {
        this.commentRepository = commentRepository;
        this.adRepository = adRepository;
        this.userRepository = userRepository;
        this.commentMapper = commentMapper;
    }

    /**
     * Получает список комментариев для указанного объявления.
     *
     * @param adId идентификатор объявления
     * @return список комментариев
     * @throws RuntimeException если объявление не найдено
     */
    public List<Comment> getCommentsByAd(Integer adId) {
        AdEntity ad = adRepository.findById(adId)
                .orElseThrow(() -> new RuntimeException("Ad not found"));

        return commentRepository.findByAd(ad)
                .stream()
                .map(commentMapper::toDto)
                .collect(Collectors.toList());
    }

    /**
     * Добавляет новый комментарий к объявлению.
     *
     * @param adId идентификатор объявления
     * @param dto данные комментария
     * @param username имя пользователя (автор комментария)
     * @return созданный комментарий
     * @throws RuntimeException если объявление или пользователь не найдены
     */
    public Comment addComment(Integer adId, CreateOrUpdateComment dto, String username) {
        AdEntity ad = adRepository.findById(adId)
                .orElseThrow(() -> new RuntimeException("Ad not found"));

        UserEntity user = userRepository.findByUsername(username)
                .orElseThrow(() -> new RuntimeException("User not found"));

        CommentEntity entity = commentMapper.toEntity(dto);

        entity.setAd(ad);
        entity.setAuthor(user);

        return commentMapper.toDto(commentRepository.save(entity));
    }

    /**
     * Удаляет комментарий.
     * Удаление доступно только владельцу комментария или администратору.
     *
     * @param commentId идентификатор комментария
     * @param username имя пользователя
     * @throws AccessDeniedException если у пользователя нет прав
     */
    public void deleteComment(Integer commentId, String username) {
        CommentEntity comment = commentRepository.findById(commentId)
                .orElseThrow(() -> new RuntimeException("Comment not found"));

        boolean isOwner = comment.getAuthor().getUsername().equals(username);

        boolean isAdmin = SecurityContextHolder.getContext()
                .getAuthentication()
                .getAuthorities()
                .stream()
                .anyMatch(a -> a.getAuthority().equals("ROLE_ADMIN"));

        if (!isOwner && !isAdmin) {
            throw new AccessDeniedException("Нет прав");
        }

        commentRepository.delete(comment);
    }
}
