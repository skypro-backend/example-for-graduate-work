package ru.skypro.homework.service.impl;

import lombok.RequiredArgsConstructor;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Service;
import ru.skypro.homework.dto.Comment;
import ru.skypro.homework.dto.Comments;
import ru.skypro.homework.dto.CreateOrUpdateComment;
import ru.skypro.homework.entity.Ad;
import ru.skypro.homework.entity.User;
import ru.skypro.homework.exception.NotFoundException;
import ru.skypro.homework.mapper.CommentMapper;
import ru.skypro.homework.repository.AdRepository;
import ru.skypro.homework.repository.CommentRepository;
import ru.skypro.homework.service.utils.SecurityUtils;
import ru.skypro.homework.service.CommentService;

import java.time.LocalDateTime;
import java.util.List;

@Service
@RequiredArgsConstructor
public class CommentServiceImpl implements CommentService {

    private final CommentRepository commentRepository;
    private final AdRepository adRepository;
    private final CommentMapper commentMapper;
    private final SecurityUtils securityUtils;

    @Override
    public Comments getComments(Integer adId) {
        Ad ad = getAdOrThrow(adId);
        List<Comment> comments = commentRepository.findAllByAd(ad).stream()
                .map(commentMapper::toDto)
                .toList();
        return new Comments(comments.size(), comments);
    }

    @Override
    public Comment addComment(Integer adId, CreateOrUpdateComment dto, Authentication authentication) {
        Ad ad = getAdOrThrow(adId);
        User author = securityUtils.getCurrentUser(authentication);
        ru.skypro.homework.entity.Comment comment = commentMapper.toEntity(dto);
        comment.setAd(ad);
        comment.setAuthor(author);
        comment.setCreatedAt(LocalDateTime.now());
        return commentMapper.toDto(commentRepository.save(comment));
    }

    @Override
    public void deleteComment(Integer adId, Integer commentId, Authentication authentication) {
        ru.skypro.homework.entity.Comment comment = getCommentOrThrow(adId, commentId);
        securityUtils.checkOwnership(comment.getAuthor(), authentication);
        commentRepository.delete(comment);
    }

    @Override
    public Comment updateComment(Integer adId, Integer commentId, CreateOrUpdateComment dto,
                                 Authentication authentication) {
        ru.skypro.homework.entity.Comment comment = getCommentOrThrow(adId, commentId);
        securityUtils.checkOwnership(comment.getAuthor(), authentication);
        comment.setText(dto.getText());
        return commentMapper.toDto(commentRepository.save(comment));
    }

    private Ad getAdOrThrow(Integer adId) {
        return adRepository.findById(adId)
                .orElseThrow(() -> new NotFoundException("Объявление не найдено"));
    }

    private ru.skypro.homework.entity.Comment getCommentOrThrow(Integer adId, Integer commentId) {
        ru.skypro.homework.entity.Comment comment = commentRepository.findById(commentId)
                .orElseThrow(() -> new NotFoundException("Комментарий не найден"));
        if (!comment.getAd().getId().equals(adId)) {
            throw new NotFoundException("Комментарий не найден для данного объявления");
        }
        return comment;
    }
}
