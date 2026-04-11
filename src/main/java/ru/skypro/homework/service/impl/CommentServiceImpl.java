package ru.skypro.homework.service.impl;

import org.springframework.http.HttpStatus;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;
import ru.skypro.homework.dto.Comment;
import ru.skypro.homework.dto.Comments;
import ru.skypro.homework.dto.CreateOrUpdateComment;
import ru.skypro.homework.entity.AdvertisementEntity;
import ru.skypro.homework.entity.CommentEntity;
import ru.skypro.homework.entity.UserEntity;
import ru.skypro.homework.mapper.CommentMapper;
import ru.skypro.homework.repository.AdvertisementRepository;
import ru.skypro.homework.repository.CommentRepository;
import ru.skypro.homework.repository.UserRepository;
import ru.skypro.homework.service.CommentService;

import java.time.LocalDateTime;
import java.util.List;

/**
 * Реализация сервиса для работы с комментариями.
 * Включает операции получения комментариев по объявлению, добавление, обновление и удаление комментариев.
 * Использует проверку доступа для редактирования и удаления комментариев.
 */
@Service
public class CommentServiceImpl implements CommentService {

    private final CommentRepository commentRepository;
    private final CommentMapper commentMapper;
    private final UserRepository userRepository;
    private final AdvertisementRepository advertisementRepository;

    public CommentServiceImpl(CommentRepository commentRepository,
                              CommentMapper commentMapper,
                              UserRepository userRepository,
                              AdvertisementRepository advertisementRepository) {
        this.commentRepository = commentRepository;
        this.commentMapper = commentMapper;
        this.userRepository = userRepository;
        this.advertisementRepository = advertisementRepository;
    }

    @Override
    public Comments getCommentsByAdId(Integer adId) {
        List<CommentEntity> commentEntities = commentRepository.findByAdId(adId);
        List<Comment> results = commentEntities.stream()
                .map(commentMapper::toDto)
                .toList();
        return Comments.builder()
                .count(results.size())
                .results(results)
                .build();
    }

    @Override
    public Comment addComment(Integer adId, CreateOrUpdateComment commentRequest, String authorEmail) {
        AdvertisementEntity ad = advertisementRepository.findById(adId)
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Объявление не найдено"));

        UserEntity author = userRepository.findByEmail(authorEmail)
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Пользователь не найден"));

        CommentEntity entity = commentMapper.toEntity(commentRequest);
        entity.setCreatedAt(LocalDateTime.now());
        entity.setAuthor(author);
        entity.setAd(ad);

        CommentEntity saved = commentRepository.save(entity);
        return commentMapper.toDto(saved);
    }

    @PreAuthorize("@commentSecurity.hasAccess(#commentId)")
    @Override
    public Comment updateComment(Integer adId, Integer commentId, CreateOrUpdateComment commentRequest) {

        CommentEntity comment = commentRepository.findById(commentId)
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Комментарий не найден"));

        if (!comment.getAd().getId().equals(adId)) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "Комментарий не принадлежит объявлению");
        }

        comment.setText(commentRequest.getText());

        CommentEntity updated = commentRepository.save(comment);
        return commentMapper.toDto(updated);
    }

    @PreAuthorize("@commentSecurity.hasAccess(#commentId)")
    @Override
    public void deleteComment(Integer adId, Integer commentId) {

        CommentEntity comment = commentRepository.findById(commentId)
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Комментарий не найден"));

        if (!comment.getAd().getId().equals(adId)) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "Комментарий не принадлежит объявлению");
        }

        commentRepository.delete(comment);
    }
}