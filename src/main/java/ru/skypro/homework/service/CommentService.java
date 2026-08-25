package ru.skypro.homework.service;

import lombok.RequiredArgsConstructor;
import org.springframework.security.access.AccessDeniedException;
import org.springframework.stereotype.Service;
import ru.skypro.homework.dto.Comment;
import ru.skypro.homework.dto.CreateOrUpdateComment;
import ru.skypro.homework.mapper.CommentMapper;
import ru.skypro.homework.model.AdEntity;
import ru.skypro.homework.model.CommentEntity;
import ru.skypro.homework.model.UserEntity;
import ru.skypro.homework.repository.AdRepository;
import ru.skypro.homework.repository.CommentRepository;
import ru.skypro.homework.repository.UserRepository;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class CommentService {

    private final CommentRepository commentRepository;
    private final AdRepository adRepository;
    private final UserRepository userRepository;
    private final CommentMapper commentMapper;

    public List<Comment> getCommentsByAdId(Integer adId) {
        return commentRepository.findAll().stream()
                .filter(comment -> comment.getAd().getId().equals(adId))
                .map(commentMapper::toDto)
                .filter(Optional::isPresent)
                .map(Optional::get)
                .collect(Collectors.toList());
    }

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

    public Optional<Comment> updateComment(Integer adId, Integer commentId, CreateOrUpdateComment commentDto, Integer currentUserId) {
        return commentRepository.findById(commentId)
                .filter(comment -> comment.getAd().getId().equals(adId))
                .filter(comment -> {
                    if (comment.getAuthor().getId().equals(currentUserId)) {
                        return true;
                    }
                    throw new AccessDeniedException("You are not the author of this comment");
                })
                .map(entity -> {
                    entity.setText(commentDto.getText());
                    return commentRepository.save(entity);
                })
                .flatMap(commentMapper::toDto);
    }

    public boolean deleteComment(Integer adId, Integer commentId, Integer currentUserId) {
        return commentRepository.findById(commentId)
                .filter(comment -> comment.getAd().getId().equals(adId))
                .map(comment -> {
                    if (!comment.getAuthor().getId().equals(currentUserId)) {
                        throw new AccessDeniedException("You are not the author of this comment");
                    }
                    commentRepository.delete(comment);
                    return true;
                })
                .orElse(false);
    }

}
