package ru.skypro.homework.service;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import ru.skypro.homework.dto.CommentDto;
import ru.skypro.homework.dto.CreateOrUpdateCommentDto;
import ru.skypro.homework.entity.Ad;
import ru.skypro.homework.entity.Comment;
import ru.skypro.homework.entity.User;
import ru.skypro.homework.mapper.CommentMapper;
import ru.skypro.homework.repository.AdRepository;
import ru.skypro.homework.repository.CommentRepository;
import ru.skypro.homework.repository.UserRepository;

import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class CommentService {

    private final CommentRepository commentRepository;
    private final AdRepository adRepository;
    private final UserRepository userRepository;
    private final CommentMapper commentMapper;

    @Transactional(readOnly = true)
    public List<CommentDto> getCommentsByAdId(Integer adId) {
        Ad ad = adRepository.findById(adId)
                .orElseThrow(() -> new RuntimeException("Объявление не найдено"));
        return commentRepository.findByAdOrderByCreatedAtAsc(ad).stream()
                .map(commentMapper::toDto)
                .collect(Collectors.toList());
    }

    @Transactional
    public CommentDto addComment(Integer adId, CreateOrUpdateCommentDto createOrUpdateCommentDto, String email) {
        Ad ad = adRepository.findById(adId)
                .orElseThrow(() -> new RuntimeException("Объявление не найдено"));
        User author = userRepository.findByEmail(email)
                .orElseThrow(() -> new RuntimeException("Пользователь не найден"));
        Comment comment = commentMapper.toEntity(new CommentDto());
        commentMapper.updateEntityFromDto(createOrUpdateCommentDto, comment);
        comment.setAd(ad);
        comment.setAuthor(author);
        comment.setCreatedAt(System.currentTimeMillis());
        return commentMapper.toDto((commentRepository.save(comment)));
    }

    @Transactional
    public void deleteComment(Integer adId, Integer commentId, String email) {
        Comment comment = commentRepository.findById(commentId)
                .orElseThrow(() -> new RuntimeException("Комментарий не найден"));
        if (!comment.getAd().getPk().equals(adId)) {
            throw new RuntimeException("Комментарий не относится к объявлению");
        }
        boolean isCommentAuthor = comment.getAuthor().getEmail().equals(email);
        boolean isAdAuthor = comment.getAuthor().getEmail().equals(email);
        if (!isCommentAuthor && !isAdAuthor) {
            throw new RuntimeException("Запрещено");
        }
        commentRepository.deleteById(commentId);
    }

    @Transactional
    public CommentDto updateComment(Integer adId, Integer commentId, CreateOrUpdateCommentDto createOrUpdateCommentDto, String email) {
        Comment comment = commentRepository.findById(commentId)
                .orElseThrow(() -> new RuntimeException("Комментарий не найден"));
        if (!comment.getAd().getPk().equals(adId)) {
            throw new RuntimeException("Комментарий не относится к объявлению");
        }
        if (comment.getAuthor().getEmail().equals(email)) {
            throw new RuntimeException("Запрещено");
        }
        commentMapper.updateEntityFromDto(createOrUpdateCommentDto, comment);
        return commentMapper.toDto(commentRepository.save(comment));
    }
}
