package ru.skypro.homework.service;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import ru.skypro.homework.dto.CommentDto;
import ru.skypro.homework.dto.CreateOrUpdateCommentDto;
import ru.skypro.homework.entity.AdEntity;
import ru.skypro.homework.entity.CommentEntity;
import ru.skypro.homework.entity.UserEntity;
import ru.skypro.homework.mapper.CommentMapper;
import ru.skypro.homework.repository.AdRepository;
import ru.skypro.homework.repository.CommentRepository;

import javax.persistence.EntityNotFoundException;
import java.time.LocalDateTime;
import java.util.List;


@Slf4j
@Service
@RequiredArgsConstructor
@Transactional
public class CommentService {

    private final CommentRepository commentRepository;
    private final AdRepository adRepository;
    private final CommentMapper commentMapper;


    @Transactional(readOnly = true)
    public List<CommentDto> getCommentsByAdId(Integer adId) {
        List<CommentEntity> comments = commentRepository.findByAdIdOrderByCreatedAtDesc(adId);
        return commentMapper.toDtoList(comments);
    }


    public CommentDto createComment(Integer adId, CreateOrUpdateCommentDto dto, UserEntity author) {
        AdEntity ad = adRepository.findById(adId)
                .orElseThrow(() -> new EntityNotFoundException("Объявление не найдено: " + adId));
        CommentEntity entity = commentMapper.toEntity(dto);
        entity.setAd(ad);
        entity.setAuthor(author);
        entity.setCreatedAt(LocalDateTime.now());
        entity = commentRepository.save(entity);
        log.info("Создан комментарий к объявлению {} от пользователя {}", adId, author.getEmail());
        return commentMapper.toDto(entity);
    }


    public CommentDto updateComment(Integer adId, Integer commentId, CreateOrUpdateCommentDto dto,
                                    String currentUserEmail, boolean isAdmin) {
        CommentEntity entity = commentRepository.findById(commentId)
                .orElseThrow(() -> new EntityNotFoundException("Комментарий не найден: " + commentId));
        if (!isAdmin && !entity.getAuthor().getEmail().equalsIgnoreCase(currentUserEmail)) {
            throw new SecurityException("Нет прав на редактирование комментария");
        }
        commentMapper.updateEntity(entity, dto);
        entity = commentRepository.save(entity);
        log.info("Обновлён комментарий: {}", commentId);
        return commentMapper.toDto(entity);
    }


    public void deleteComment(Integer adId, Integer commentId, String currentUserEmail, boolean isAdmin) {
        CommentEntity entity = commentRepository.findById(commentId)
                .orElseThrow(() -> new EntityNotFoundException("Комментарий не найден: " + commentId));
        if (!isAdmin && !entity.getAuthor().getEmail().equalsIgnoreCase(currentUserEmail)) {
            throw new SecurityException("Нет прав на удаление комментария");
        }
        commentRepository.delete(entity);
        log.info("Удалён комментарий: {}", commentId);
    }
}