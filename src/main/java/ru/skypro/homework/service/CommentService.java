package ru.skypro.homework.service;

import lombok.RequiredArgsConstructor;
import org.springframework.security.access.AccessDeniedException;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import ru.skypro.homework.dto.Comment;
import ru.skypro.homework.dto.CreateOrUpdateComment;
import ru.skypro.homework.mapper.CommentMapper;
import ru.skypro.homework.model.UserEntity;
import ru.skypro.homework.repository.AdRepository;
import ru.skypro.homework.repository.CommentRepository;
import ru.skypro.homework.repository.UserRepository;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

/**
 * Сервис для управления комментариями.
 * Содержит бизнес-логику по созданию, обновлению, удалению и получению комментариев.
 * Основные функции:
 * - получение всех комментариев к объявлению;
 * - добавление нового комментария;
 * - обновление комментария с проверкой прав доступа;
 * - удаление комментария с проверкой прав доступа.
 */
@Service
@RequiredArgsConstructor
public class CommentService {

    private final CommentRepository commentRepository;
    private final AdRepository adRepository;
    private final UserRepository userRepository;
    private final CommentMapper commentMapper;

    /**
     * Получить все комментарии к объявлению.
     * @param adId ID объявления
     * @return список комментариев
     */
    public List<Comment> getCommentsByAdId(Integer adId) {
        return commentRepository.findByAdId(adId).stream()
                .map(commentMapper::toDto)
                .filter(Optional::isPresent)
                .map(Optional::get)
                .collect(Collectors.toList());
    }

    /**
     * Добавить новый комментарий.
     * @param adId       ID объявления
     * @param commentDto DTO с текстом комментария
     * @param authorId   ID автора
     * @return Optional с созданным комментарием
     */
    public Optional<Comment> addComment(Integer adId, CreateOrUpdateComment commentDto, Integer authorId) {
        return adRepository.findById(adId)
                .flatMap(ad -> userRepository.findById(authorId)
                        .flatMap(author -> commentMapper.toEntity(commentDto)
                                .map(entity -> {
                                    entity.setAd(ad);
                                    entity.setAuthor(author);
                                    return commentRepository.save(entity);
                                })))
                .flatMap(commentMapper::toDto);
    }

    /**
     * Обновить комментарий.
     * @param adId           ID объявления
     * @param commentId      ID комментария
     * @param commentDto     DTO с новым текстом
     * @param authentication данные аутентификации
     * @return Optional с обновлённым комментарием
     * @throws AccessDeniedException если пользователь не автор и не ADMIN
     */
    public Optional<Comment> updateComment(Integer adId, Integer commentId, CreateOrUpdateComment commentDto,  Authentication authentication) {
        return commentRepository.findById(commentId)
                .filter(comment -> comment.getAd().getId().equals(adId))
                .map(comment -> {
                    if (!hasAdminRole(authentication) && !comment.getAuthor().getId().equals(getUserId(authentication))) {
                        throw new AccessDeniedException("You are not the author of this comment");
                    }
                    comment.setText(commentDto.getText());
                    return commentRepository.save(comment);
                })
                .flatMap(commentMapper::toDto);
    }

    /**
     * Удалить комментарий.
     * @param adId           ID объявления
     * @param commentId      ID комментария
     * @param authentication данные аутентификации
     * @return true если удаление успешно, иначе false
     * @throws AccessDeniedException если пользователь не автор и не ADMIN
     */
    public boolean deleteComment(Integer adId, Integer commentId,  Authentication authentication) {
        return commentRepository.findById(commentId)
                .filter(comment -> comment.getAd().getId().equals(adId))
                .map(comment -> {
                    if (!hasAdminRole(authentication) && !comment.getAuthor().getId().equals(getUserId(authentication))) {
                        throw new AccessDeniedException("You are not the author of this comment");
                    }
                    commentRepository.delete(comment);
                    return true;
                })
                .orElse(false);
    }

    /**
     * Проверить, имеет ли пользователь роль ADMIN.
     * @param authentication данные аутентификации
     * @return true если роль ADMIN, иначе false
     */
    private boolean hasAdminRole(Authentication authentication) {
        if (authentication == null) {
            return false;
        }
        return authentication.getAuthorities().stream()
                .anyMatch(auth -> auth.getAuthority().equals("ROLE_ADMIN"));
    }

    /**
     * Получить ID пользователя из аутентификации.
     * @param authentication данные аутентификации
     * @return ID пользователя
     * @throws AccessDeniedException если аутентификация отсутствует
     * @throws UsernameNotFoundException если пользователь не найден
     */
    private Integer getUserId(Authentication authentication) {
        if (authentication == null) {
            throw new AccessDeniedException("User not authenticated");
        }
        String email = authentication.getName();
        return userRepository.findByEmail(email)
                .map(UserEntity::getId)
                .orElseThrow(() -> new UsernameNotFoundException("User not found with email: " + email));
    }
}
