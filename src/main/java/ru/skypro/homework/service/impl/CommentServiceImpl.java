package ru.skypro.homework.service.impl;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import ru.skypro.homework.dto.Comment;
import ru.skypro.homework.dto.Comments;
import ru.skypro.homework.dto.CreateOrUpdateComment;
import ru.skypro.homework.entity.AdEntity;
import ru.skypro.homework.entity.CommentEntity;
import ru.skypro.homework.entity.UserEntity;
import ru.skypro.homework.mapper.CommentMapper;
import ru.skypro.homework.repository.AdRepository;
import ru.skypro.homework.repository.CommentRepository;
import ru.skypro.homework.repository.UserRepository;
import ru.skypro.homework.service.CommentService;

import java.util.Optional;

@Slf4j
@Service
@RequiredArgsConstructor
public class CommentServiceImpl implements CommentService {

    private final CommentRepository commentRepository;
    private final AdRepository adRepository;
    private final UserRepository userRepository;
    private final CommentMapper commentMapper;

    @Override
    @Transactional(readOnly = true)
    public Comments getCommentsByAdId(Integer adId) {
        log.debug("Getting comments for ad id: {}", adId);

        if (!adRepository.existsById(adId)) {
            log.warn("Ad not found with id: {}", adId);
            return new Comments();
        }

        var comments = commentRepository.findByAdId(adId);
        return commentMapper.toCommentsDto(comments);
    }

    @Override
    @Transactional
    public Optional<Comment> addComment(Integer adId, CreateOrUpdateComment commentDto, String userEmail) {
        log.info("Adding comment to ad {} by user {}", adId, userEmail);

        AdEntity ad = adRepository.findById(adId)
                .orElseThrow(() -> new RuntimeException("Ad not found with id: " + adId));

        UserEntity author = userRepository.findByEmail(userEmail)
                .orElseThrow(() -> new UsernameNotFoundException("User not found: " + userEmail));

        CommentEntity comment = commentMapper.toEntity(commentDto);
        comment.setAuthor(author);
        comment.setAd(ad);
        comment.setCreatedAt(System.currentTimeMillis());

        CommentEntity saved = commentRepository.save(comment);
        log.info("Comment added successfully with id: {}", saved.getId());

        return Optional.of(commentMapper.toDto(saved));
    }

    @Override
    @Transactional
    public boolean deleteComment(Integer commentId) {
        log.info("Deleting comment with id: {}", commentId);

        if (!commentRepository.existsById(commentId)) {
            log.warn("Comment not found with id: {}", commentId);
            return false;
        }

        commentRepository.deleteById(commentId);
        log.info("Comment deleted successfully with id: {}", commentId);
        return true;
    }

    @Override
    @Transactional
    public Optional<Comment> updateComment(Integer commentId, CreateOrUpdateComment commentDto) {
        log.info("Updating comment with id: {}", commentId);

        CommentEntity comment = commentRepository.findById(commentId)
                .orElseThrow(() -> new RuntimeException("Comment not found with id: " + commentId));

        comment.setText(commentDto.getText());

        CommentEntity updated = commentRepository.save(comment);
        log.info("Comment updated successfully with id: {}", commentId);

        return Optional.of(commentMapper.toDto(updated));
    }

    @Override
    @Transactional(readOnly = true)
    public boolean isCommentOwner(Integer commentId, String email) {
        log.debug("Checking if user {} is owner of comment {}", email, commentId);

        return commentRepository.findById(commentId)
                .map(comment -> comment.getAuthor().getEmail().equals(email))
                .orElse(false);
    }

    private boolean isAdmin(String email) {
        return userRepository.findByEmail(email)
                .map(user -> "ADMIN".equals(user.getRole()))
                .orElse(false);
    }
}