package ru.skypro.homework.service;

import lombok.RequiredArgsConstructor;
import org.springframework.security.access.AccessDeniedException;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import ru.skypro.homework.dto.Comment;
import ru.skypro.homework.dto.Comments;
import ru.skypro.homework.dto.CreateOrUpdateComment;
import ru.skypro.homework.dto.Role;
import ru.skypro.homework.entity.AdEntity;
import ru.skypro.homework.entity.CommentEntity;
import ru.skypro.homework.entity.UserEntity;
import ru.skypro.homework.exception.ResourceNotFoundException;
import ru.skypro.homework.mapper.CommentMapper;
import ru.skypro.homework.repository.CommentRepository;

import java.time.Instant;
import java.util.List;

@Service
@RequiredArgsConstructor
public class CommentService {

    private final CommentRepository commentRepository;
    private final CommentMapper commentMapper;
    private final AdService adService;
    private final UserService userService;

    @Transactional(readOnly = true)
    public Comments getComments(Integer adId) {
        adService.getAdEntityById(adId);
        List<Comment> commentList = commentMapper.toDtoList(commentRepository.findAllByAdId(adId));
        Comments comments = new Comments();
        comments.setCount(commentList.size());
        comments.setResults(commentList);
        return comments;
    }

    @Transactional
    public Comment addComment(Integer adId, CreateOrUpdateComment dto, Authentication authentication) {
        AdEntity ad = adService.getAdEntityById(adId);
        UserEntity author = userService.getUserEntityByEmail(authentication.getName());

        CommentEntity comment = commentMapper.toEntity(dto);
        comment.setAd(ad);
        comment.setAuthor(author);
        comment.setCreatedAt(Instant.now().toEpochMilli());

        return commentMapper.toDto(commentRepository.save(comment));
    }

    @Transactional
    public void deleteComment(Integer adId, Integer commentId, Authentication authentication) {
        CommentEntity comment = getCommentEntity(adId, commentId);
        checkAccess(comment, authentication);
        commentRepository.delete(comment);
    }

    @Transactional
    public Comment updateComment(Integer adId, Integer commentId, CreateOrUpdateComment dto, Authentication authentication) {
        CommentEntity comment = getCommentEntity(adId, commentId);
        checkAccess(comment, authentication);
        comment.setText(dto.getText());
        return commentMapper.toDto(commentRepository.save(comment));
    }

    private CommentEntity getCommentEntity(Integer adId, Integer commentId) {
        adService.getAdEntityById(adId);
        CommentEntity comment = commentRepository.findById(commentId)
                .orElseThrow(() -> new ResourceNotFoundException("Комментарий не найден: " + commentId));
        if (!comment.getAd().getId().equals(adId)) {
            throw new ResourceNotFoundException("Комментарий не относится к указанному объявлению");
        }
        return comment;
    }

    private void checkAccess(CommentEntity comment, Authentication authentication) {
        UserEntity currentUser = userService.getUserEntityByEmail(authentication.getName());
        boolean isAdmin = currentUser.getRole() == Role.ADMIN;
        boolean isAuthor = comment.getAuthor().equals(currentUser);
        if (!isAdmin && !isAuthor) {
            throw new AccessDeniedException("Недостаточно прав для изменения или удаления комментария");
        }
    }
}