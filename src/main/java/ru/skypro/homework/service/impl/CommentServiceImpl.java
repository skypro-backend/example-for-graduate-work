package ru.skypro.homework.service.impl;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import ru.skypro.homework.dto.Comment;
import ru.skypro.homework.dto.Comments;
import ru.skypro.homework.dto.CreateOrUpdateComment;
import ru.skypro.homework.dto.Role;
import ru.skypro.homework.entity.AdEntity;
import ru.skypro.homework.entity.CommentEntity;
import ru.skypro.homework.entity.UserEntity;
import ru.skypro.homework.mapper.CommentMapper;
import ru.skypro.homework.repository.AdRepository;
import ru.skypro.homework.repository.CommentRepository;
import ru.skypro.homework.repository.UserRepository;
import ru.skypro.homework.service.CommentService;

import java.time.Instant;
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
        List<Comment> commentList = commentRepository.findAllByAdId(adId).stream()
                .map(commentMapper::toDto)
                .collect(Collectors.toList());

        Comments comments = new Comments();
        comments.setCount(commentList.size());
        comments.setResults(commentList);
        return comments;
    }

    @Override
    @Transactional
    public Comment addComment(Integer adId, CreateOrUpdateComment dto, String username) {
        AdEntity ad = adRepository.findById(adId)
                .orElseThrow(() -> new IllegalArgumentException("Ad not found"));
        UserEntity author = userRepository.findByEmail(username)
                .orElseThrow(() -> new IllegalArgumentException("User not found"));

        CommentEntity commentEntity = commentMapper.toEntity(dto);
        commentEntity.setAd(ad);
        commentEntity.setAuthor(author);
        commentEntity.setCreatedAt(Instant.now().toEpochMilli());

        return commentMapper.toDto(commentRepository.save(commentEntity));
    }

    @Override
    @Transactional
    public void deleteComment(Integer commentId) {
        commentRepository.deleteById(commentId);
    }

    @Override
    @Transactional
    public Comment updateComment(Integer commentId, CreateOrUpdateComment dto) {
        CommentEntity comment = commentRepository.findById(commentId)
                .orElseThrow(() -> new IllegalArgumentException("Comment not found"));

        comment.setText(dto.getText());
        return commentMapper.toDto(commentRepository.save(comment));
    }

    @Override
    public boolean isCommentAuthorOrAdmin(Integer commentId, String username) {
        CommentEntity comment = commentRepository.findById(commentId)
                .orElseThrow(() -> new IllegalArgumentException("Comment not found"));

        UserEntity user = userRepository.findByEmail(username)
                .orElseThrow(() -> new IllegalArgumentException("User not found"));

        boolean isAuthor = comment.getAuthor().getEmail().equals(username);
        boolean isAdmin = user.getRole() == Role.ADMIN;

        return isAuthor || isAdmin;
    }
}