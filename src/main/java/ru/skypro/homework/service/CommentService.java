package ru.skypro.homework.service;

import org.springframework.security.access.AccessDeniedException;
import org.springframework.stereotype.Service;
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

import java.time.Instant;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class CommentService {

    private final CommentRepository commentRepository;
    private final AdRepository adRepository;
    private final UserRepository userRepository;
    private final CommentMapper commentMapper;

    public CommentService(
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

    public Comments getComments(Integer adId) {
        List<Comment> comments =
                commentRepository.findAllByAdId(adId)
                        .stream()
                        .map(commentMapper::toDto)
                        .collect(Collectors.toList());

        Comments result = new Comments();
        result.setCount(comments.size());
        result.setResults(comments);

        return result;
    }

    public Comment addComment(
            Integer adId,
            String username,
            CreateOrUpdateComment dto
    ) {
        AdEntity ad = adRepository.findById(adId)
                .orElseThrow(() ->
                        new IllegalArgumentException("Ad not found")
                );

        UserEntity user = getUser(username);

        CommentEntity entity = new CommentEntity();
        entity.setText(dto.getText());
        entity.setCreatedAt(Instant.now().toEpochMilli());
        entity.setAuthor(user);
        entity.setAd(ad);

        return commentMapper.toDto(
                commentRepository.save(entity)
        );
    }

    public Comment updateComment(
            Integer adId,
            Integer commentId,
            CreateOrUpdateComment dto,
            String username
    ) {
        CommentEntity entity =
                getComment(adId, commentId);

        UserEntity currentUser = getUser(username);

        checkAccess(entity, currentUser);

        entity.setText(dto.getText());

        return commentMapper.toDto(
                commentRepository.save(entity)
        );
    }

    public void deleteComment(
            Integer adId,
            Integer commentId,
            String username
    ) {
        CommentEntity entity =
                getComment(adId, commentId);

        UserEntity currentUser = getUser(username);

        checkAccess(entity, currentUser);

        commentRepository.delete(entity);
    }

    private CommentEntity getComment(
            Integer adId,
            Integer commentId
    ) {
        return commentRepository
                .findByIdAndAdId(commentId, adId)
                .orElseThrow(() ->
                        new IllegalArgumentException(
                                "Comment not found"
                        )
                );
    }

    private UserEntity getUser(String username) {
        return userRepository.findByUsername(username)
                .orElseThrow(() ->
                        new IllegalArgumentException("User not found")
                );
    }

    private void checkAccess(
            CommentEntity comment,
            UserEntity user
    ) {
        boolean isOwner =
                comment.getAuthor().getId().equals(user.getId());

        boolean isAdmin =
                user.getRole() == Role.ADMIN;

        if (!isOwner && !isAdmin) {
            throw new AccessDeniedException("Access denied");
        }
    }
}