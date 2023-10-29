package ru.skypro.homework.service.impl;


import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import ru.skypro.homework.dto.Comment;
import ru.skypro.homework.dto.Comments;
import ru.skypro.homework.dto.CreateOrUpdateComment;
import ru.skypro.homework.dto.Role;
import ru.skypro.homework.entity.AdEntity;
import ru.skypro.homework.entity.CommentEntity;
import ru.skypro.homework.entity.UserEntity;
import ru.skypro.homework.exceptions.AdNotFoundException;
import ru.skypro.homework.exceptions.CommentNotFoundException;
import ru.skypro.homework.exceptions.PermissionDeniedException;
import ru.skypro.homework.mapper.CommentMapper;
import ru.skypro.homework.repository.AdRepository;
import ru.skypro.homework.repository.CommentRepository;
import ru.skypro.homework.repository.UserRepository;
import ru.skypro.homework.service.CommentsService;

import java.util.List;

@Service
@Slf4j
@RequiredArgsConstructor
public class CommentServiceImpl implements CommentsService {

    private final CommentRepository commentRepository;
    private final UserRepository userRepository;
    private final AdRepository adsRepository;
    private final CommentMapper commentMapper;

    @Override
    public Comments getComments(int id, String username) {
        try {
            if (!adsRepository.existsById(id)) {
                throw new AdNotFoundException();
            }
            List<CommentEntity> commentEntityList = commentRepository.findCommentEntitiesByAdId(id);
            List<Comment> commentList = commentEntityList.stream()
                    .map(commentMapper::commentEntityToComment)
                    .toList();
            int count = commentList.size();
            return new Comments(count, commentList);
        } catch (AdNotFoundException e) {
            log.error("Ad with id = {} is not found", id, e);
            throw new AdNotFoundException();
        }
    }

    @Override
    public Comment addComment(int id, CreateOrUpdateComment createOrUpdateComment, String username) {
        try {
            UserEntity userEntity = userRepository.findByUsername(username);
            AdEntity adEntity = adsRepository.findById(id).orElseThrow(AdNotFoundException::new);
            log.info("Ad with id = {} is  found", id);
            CommentEntity commentEntity = commentMapper.createOrUpdateCommentToCommentEntity(createOrUpdateComment);
            commentEntity.setUser(userEntity);
            commentEntity.setAd(adEntity);
            commentRepository.save(commentEntity);
            return commentMapper.commentEntityToComment(commentEntity);
        } catch (AdNotFoundException e) {
            log.error("Ad with id = {} is not found", id, e);
            throw new AdNotFoundException();
        }
    }

    @Override
    public void deleteComment(int adId, int commentId, String username) {
        try {
            CommentEntity commentEntity = getCommentEntityFromDatabase(commentId, adId);
            if (!checkPermission(commentEntity, username)) {
                throw new PermissionDeniedException();
            }
            commentRepository.delete(commentEntity);
            log.info("Comment with id = {} was deleted", commentId);
        } catch (PermissionDeniedException e) {
            log.error("You have no permission to delete comment with id = {}", commentId,  e);
            throw new PermissionDeniedException();
        }
    }

    @Override
    public Comment updateComment(int adId, int commentId, CreateOrUpdateComment createOrUpdateComment,
                                 String username) {
        try {
            CommentEntity commentEntity = getCommentEntityFromDatabase(commentId, adId);
            if (!checkPermission(commentEntity, username)) {
                throw new PermissionDeniedException();
            }
            commentEntity.setText(createOrUpdateComment.getText());
            commentRepository.save(commentEntity);
            return commentMapper.commentEntityToComment(commentEntity);
        } catch (PermissionDeniedException e) {
            log.error("You have no permission to update comment with id = {}", commentId,  e);
            throw new PermissionDeniedException();
        }
    }

    private boolean checkPermission(CommentEntity commentEntity, String username) {
        UserEntity userEntity = commentEntity.getUser();
        UserEntity currentUserEntity = userRepository.findByUsername(username);
        return userEntity.getUsername().equals(currentUserEntity.getUsername()) ||
                currentUserEntity.getRole().equals(Role.ADMIN);
    }

    private CommentEntity getCommentEntityFromDatabase(int commentId, int adId) {
        try {
            checkAdExistenceById(adId);
            CommentEntity commentEntity = commentRepository.findCommentEntityByIdAndAdId(commentId, adId);
            if (commentEntity == null) {
                throw new CommentNotFoundException();
            }
            log.info("Comment with id = {} is found", commentId);
            return commentEntity;
        } catch (CommentNotFoundException e) {
            log.error("Comment with id = {} is not found", commentId,  e);
            throw new CommentNotFoundException();
        }
    }

    public void checkAdExistenceById(int id) {
        try {
            if (!adsRepository.existsById(id)) {
                throw new AdNotFoundException();
            }
        } catch (AdNotFoundException e) {
            log.error("Ad with id = {} is not found", id, e);
            throw new AdNotFoundException();
        }
    }
}
