package ru.skypro.homework.service.impl;

import lombok.RequiredArgsConstructor;
import org.springframework.security.access.AccessDeniedException;
import org.springframework.stereotype.Service;
import ru.skypro.homework.dto.Comment;
import ru.skypro.homework.dto.Comments;
import ru.skypro.homework.entity.AdEntity;
import ru.skypro.homework.entity.CommentEntity;
import ru.skypro.homework.entity.UserEntity;
import ru.skypro.homework.exception.EntityNotFoundException;
import ru.skypro.homework.mapper.CommentMapper;
import ru.skypro.homework.repository.AdRepository;
import ru.skypro.homework.repository.CommentRepository;
import ru.skypro.homework.repository.UserRepository;
import ru.skypro.homework.service.CommentService;

import javax.transaction.Transactional;
import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class CommentServiceImpl implements CommentService {

    private final CommentRepository commentRepository;
    private final AdRepository adRepository;
    private final UserRepository userRepository;
    private final CommentMapper commentMapper;

    @Override
    public Comments getComments(Integer adId) {
        AdEntity ad = adRepository.findById(adId).orElseThrow(() -> new EntityNotFoundException("Объявление с ID " + adId + " не найдено"));

        List<Comment> comments = commentRepository.findByAdPk(adId).stream().map(commentMapper::toDto).collect(Collectors.toList());

        Comments result = new Comments();
        result.setCount(comments.size());
        result.setResults(comments);
        return result;
    }

    @Override
    @Transactional
    public Comment addComment(Integer adId, Comment comment, String username) {
        AdEntity ad = adRepository.findById(adId).orElseThrow(() -> new EntityNotFoundException("Объявление с ID " + adId + " не найдено"));
        UserEntity author = userRepository.findByEmail(username).orElseThrow(() -> new EntityNotFoundException("Пользователь не найден"));

        CommentEntity entity = commentMapper.toEntity(comment);
        entity.setAd(ad);
        entity.setAuthor(author);

        CommentEntity saved = commentRepository.save(entity);
        return commentMapper.toDto(saved);
    }

    @Override
    @Transactional
    public void deleteComment(Integer adId, Integer commentId, String email) {
        CommentEntity comment = commentRepository.findById(commentId).orElseThrow(() -> new EntityNotFoundException("Комментарий с ID " + commentId + " не найден"));

        UserEntity user = userRepository.findByEmail(email).orElseThrow(() -> new EntityNotFoundException("Пользователь не найден"));

        if (!comment.getAuthor().getId().equals(user.getId()) && !user.getRole().name().equals("ADMIN")) {
            throw new AccessDeniedException("У вас нет прав на удаление этого комментария");
        }

        commentRepository.deleteById(commentId);
    }

    @Override
    @Transactional
    public Comment updateComment(Integer adId, Integer commentId, Comment comment, String email) {
        CommentEntity existing = commentRepository.findById(commentId).orElseThrow(() -> new EntityNotFoundException("Комментарий с ID " + commentId + " не найден"));

        UserEntity user = userRepository.findByEmail(email).orElseThrow(() -> new EntityNotFoundException("Пользователь не найден"));

        if (!existing.getAuthor().getId().equals(user.getId()) && !user.getRole().name().equals("ADMIN")) {
            throw new AccessDeniedException("У вас нет прав на редактирование этого комментария");
        }

        existing.setText(comment.getText());
        CommentEntity updated = commentRepository.save(existing);
        return commentMapper.toDto(updated);
    }
}