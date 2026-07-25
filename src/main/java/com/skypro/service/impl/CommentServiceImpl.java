package com.skypro.service.impl;

import com.skypro.dto.Comment;
import com.skypro.dto.Comments;
import com.skypro.dto.CreateOrUpdateComment;
import com.skypro.entity.AdEntity;
import com.skypro.entity.CommentEntity;
import com.skypro.entity.UserEntity;
import com.skypro.exception.AdNotFoundException;
import com.skypro.exception.CommentNotFoundException;
import com.skypro.exception.UserNotFoundException;
import com.skypro.mapper.CommentMapper;
import com.skypro.repository.AdRepository;
import com.skypro.repository.CommentRepository;
import com.skypro.repository.UserRepository;
import com.skypro.service.CommentService;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

/**
 * Реализация сервиса {@link CommentService} для управления комментариями.
 * <p>
 * Содержит бизнес-логику:
 * <ul>
 *   <li>Получение комментариев по объявлению</li>
 *   <li>Создание, обновление и удаление комментариев</li>
 *   <li>Проверка прав доступа через {@link PreAuthorize}</li>
 * </ul>
 * </p>
 */
@Service
public class CommentServiceImpl implements CommentService {

    private final CommentRepository commentRepository;
    private final AdRepository adRepository;
    private final UserRepository userRepository;
    private final CommentMapper commentMapper;

    public CommentServiceImpl(CommentRepository commentRepository,
                              AdRepository adRepository,
                              UserRepository userRepository,
                              CommentMapper commentMapper) {
        this.commentRepository = commentRepository;
        this.adRepository = adRepository;
        this.userRepository = userRepository;
        this.commentMapper = commentMapper;
    }

    /**
     * Возвращает список всех комментариев для указанного объявления.
     *
     * @param adId идентификатор объявления
     * @return объект {@link Comments} с количеством и списком комментариев
     */
    @Override
    public Comments getComments(Integer adId) {
        List<CommentEntity> commentEntities = commentRepository.findByAdId(adId);
        List<Comment> comments = commentEntities.stream()
                .map(commentMapper::toComment)
                .collect(Collectors.toList());
        return new Comments(comments.size(), comments);
    }

    /**
     * Добавляет новый комментарий к объявлению.
     *
     * @param adId                   идентификатор объявления
     * @param createOrUpdateComment  текст комментария
     * @param username               имя пользователя (автора) из аутентификации
     * @return созданный комментарий в виде {@link Comment}
     * @throws UserNotFoundException если пользователь не найден
     * @throws AdNotFoundException   если объявление не найдено
     */
    @Override
    public Comment addComment(Integer adId, CreateOrUpdateComment createOrUpdateComment, String username) {
        UserEntity user = userRepository.findByUsername(username)
                .orElseThrow(() -> new UserNotFoundException(username));
        AdEntity ad = adRepository.findById(adId)
                .orElseThrow(() -> new AdNotFoundException(adId));
        CommentEntity commentEntity = commentMapper.toEntity(createOrUpdateComment);
        commentEntity.setAuthor(user);
        commentEntity.setAd(ad);
        commentEntity.setCreatedAt(System.currentTimeMillis());
        CommentEntity saved = commentRepository.save(commentEntity);
        return commentMapper.toComment(saved);
    }

    /**
     * Удаляет комментарий по его идентификатору.
     * <p>
     * Доступно только владельцу комментария или администратору.
     * </p>
     *
     * @param adId      идентификатор объявления (используется для контекста)
     * @param commentId идентификатор комментария
     * @throws org.springframework.security.access.AccessDeniedException если пользователь не является владельцем и не ADMIN
     */
    @Override
    @PreAuthorize("@securityService.isCommentOwner(#commentId, authentication.name) || hasRole('ADMIN')")
    public void deleteComment(Integer adId, Integer commentId) {
        commentRepository.deleteById(commentId);
    }

    /**
     * Обновляет текст существующего комментария.
     * <p>
     * Доступно только владельцу комментария или администратору.
     * </p>
     *
     * @param adId                   идентификатор объявления (используется для контекста)
     * @param commentId              идентификатор комментария
     * @param createOrUpdateComment  новый текст комментария
     * @return обновлённый комментарий в виде {@link Comment}
     * @throws CommentNotFoundException если комментарий не найден
     * @throws org.springframework.security.access.AccessDeniedException если пользователь не является владельцем и не ADMIN
     */
    @Override
    @PreAuthorize("@securityService.isCommentOwner(#commentId, authentication.name) || hasRole('ADMIN')")
    public Comment updateComment(Integer adId, Integer commentId, CreateOrUpdateComment createOrUpdateComment) {
        CommentEntity commentEntity = commentRepository.findById(commentId)
                .orElseThrow(() -> new CommentNotFoundException(commentId));
        commentEntity.setText(createOrUpdateComment.getText());
        CommentEntity saved = commentRepository.save(commentEntity);
        return commentMapper.toComment(saved);
    }
}
