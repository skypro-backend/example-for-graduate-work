package ru.skypro.homework.service.impl;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import ru.skypro.homework.dto.Comment;
import ru.skypro.homework.dto.Comments;
import ru.skypro.homework.dto.CreateOrUpdateComment;
import ru.skypro.homework.entity.AdEntity;
import ru.skypro.homework.entity.CommentEntity;
import ru.skypro.homework.entity.UserEntity;
import ru.skypro.homework.exception.AccessDeniedException;
import ru.skypro.homework.mapper.CommentMapper;
import ru.skypro.homework.repository.AdRepository;
import ru.skypro.homework.repository.CommentRepository;
import ru.skypro.homework.repository.UserRepository;
import ru.skypro.homework.service.CommentService;

import javax.persistence.EntityNotFoundException;
import java.util.List;

/**
 * Реализация сервиса комментариев.
 */
@Service
public class CommentServiceImpl implements CommentService {

    private final CommentRepository commentRepository;
    private final AdRepository adRepository;
    private final UserRepository userRepository;
    private final CommentMapper commentMapper;

    public CommentServiceImpl(
            CommentRepository commentRepository,
            AdRepository adRepository,
            UserRepository userRepository,
            CommentMapper commentMapper
    ) {
        this.commentRepository = commentRepository;
        this.adRepository = adRepository;
        this.userRepository = userRepository;
        this.commentMapper = commentMapper;
    }

    /**
     * Возвращает все комментарии объявления.
     *
     * @param adId идентификатор объявления
     * @return комментарии объявления
     */
    @Override
    @Transactional(readOnly = true)
    public Comments getComments(Long adId) {
        AdEntity ad = findAd(adId);

        List<Comment> results = commentRepository
                .findAllByAdOrderByCreatedAtAsc(ad)
                .stream()
                .map(commentMapper::toDto)
                .toList();

        return new Comments(
                results.size(),
                results
        );
    }

    /**
     * Добавляет комментарий к объявлению.
     *
     * @param adId идентификатор объявления
     * @param username логин автора
     * @param request текст комментария
     * @return созданный комментарий
     */
    @Override
    @Transactional
    public Comment addComment(
            Long adId,
            String username,
            CreateOrUpdateComment request
    ) {
        AdEntity ad = findAd(adId);
        UserEntity author = findUser(username);

        CommentEntity comment =
                commentMapper.toEntity(
                        request,
                        author,
                        ad
                );

        CommentEntity savedComment =
                commentRepository.save(comment);

        return commentMapper.toDto(savedComment);
    }

    /**
     * Обновляет комментарий после проверки прав.
     *
     * @param adId идентификатор объявления
     * @param commentId идентификатор комментария
     * @param username логин пользователя
     * @param role роль пользователя
     * @param request новый текст
     * @return обновлённый комментарий
     */
    @Override
    @Transactional
    public Comment updateComment(
            Long adId,
            Long commentId,
            String username,
            String role,
            CreateOrUpdateComment request
    ) {
        AdEntity ad = findAd(adId);

        CommentEntity comment =
                findComment(commentId, ad);

        checkAccess(comment, username, role);

        commentMapper.updateEntity(
                comment,
                request
        );

        CommentEntity savedComment =
                commentRepository.save(comment);

        return commentMapper.toDto(savedComment);
    }

    /**
     * Удаляет комментарий после проверки прав.
     *
     * @param adId идентификатор объявления
     * @param commentId идентификатор комментария
     * @param username логин пользователя
     * @param role роль пользователя
     */
    @Override
    @Transactional
    public void deleteComment(
            Long adId,
            Long commentId,
            String username,
            String role
    ) {
        AdEntity ad = findAd(adId);

        CommentEntity comment =
                findComment(commentId, ad);

        checkAccess(comment, username, role);

        commentRepository.delete(comment);
    }

    private void checkAccess(
            CommentEntity comment,
            String username,
            String role
    ) {
        boolean owner = comment.getAuthor()
                .getUsername()
                .equalsIgnoreCase(username);

        boolean admin = "ADMIN".equals(role);

        if (!owner && !admin) {
            throw new AccessDeniedException(
                    "Недостаточно прав для изменения комментария"
            );
        }
    }

    private AdEntity findAd(Long adId) {
        return adRepository.findById(adId)
                .orElseThrow(() ->
                        new EntityNotFoundException(
                                "Объявление с id "
                                        + adId
                                        + " не найдено"
                        )
                );
    }

    private CommentEntity findComment(
            Long commentId,
            AdEntity ad
    ) {
        return commentRepository
                .findByIdAndAd(commentId, ad)
                .orElseThrow(() ->
                        new EntityNotFoundException(
                                "Комментарий с id "
                                        + commentId
                                        + " не найден в объявлении "
                                        + ad.getId()
                        )
                );
    }

    private UserEntity findUser(String username) {
        return userRepository
                .findByUsernameIgnoreCase(username)
                .orElseThrow(() ->
                        new EntityNotFoundException(
                                "Пользователь "
                                        + username
                                        + " не найден"
                        )
                );
    }
}
