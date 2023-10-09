package ru.skypro.homework.service.impl;


import lombok.RequiredArgsConstructor;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.stereotype.Service;
import ru.skypro.homework.dto.Comment;
import ru.skypro.homework.dto.Comments;
import ru.skypro.homework.dto.CreateOrUpdateComment;
import ru.skypro.homework.entity.AdEntity;
import ru.skypro.homework.entity.CommentEntity;
import ru.skypro.homework.entity.UserEntity;
import ru.skypro.homework.exceptions.AdNotFoundException;
import ru.skypro.homework.exceptions.CommentNotFoundException;
import ru.skypro.homework.mapper.CommentMapper;
import ru.skypro.homework.repository.AdRepository;
import ru.skypro.homework.repository.CommentRepository;
import ru.skypro.homework.repository.UserRepository;
import ru.skypro.homework.service.CommentsService;

import java.util.List;

@Service
@RequiredArgsConstructor
public class CommentServiceImpl implements CommentsService {

    private final CommentRepository commentRepository;
    private final UserRepository userRepository;
    private final AdRepository adsRepository;
    private final CommentMapper commentMapper;

    @Override
    public Comments getComments(int id, String username) {
        if (!adsRepository.existsById(id)) {
            throw new AdNotFoundException("Объявление не найдено!");
        }
        List<CommentEntity> commentEntityList = commentRepository.findCommentEntitiesByAdId(id);
        List<Comment> commentList = commentEntityList.stream()
                .map(commentMapper::commentEntityToComment)
                .toList();
        int count = commentList.size();
        return new Comments(count, commentList);
    }

    @Override
    public Comment addComment(int id, CreateOrUpdateComment createOrUpdateComment, String username) {
        UserEntity userEntity = userRepository.findByUsername(username);
        AdEntity adEntity = adsRepository.findById(id).orElseThrow(() -> new AdNotFoundException("Объявление не найдено!"));
        CommentEntity commentEntity = commentMapper.createOrUpdateCommentToCommentEntity(createOrUpdateComment);
        commentEntity.setUser(userEntity);
        commentEntity.setAd(adEntity);
        commentRepository.save(commentEntity);
        return commentMapper.commentEntityToComment(commentEntity);
    }

    @Override
    public void deleteComment(int adId, int commentId, String username) {
        CommentEntity commentEntity = getCommentEntityFromDatabase(commentId, adId);
        if (!checkPermission(commentEntity, username)) {
            throw new BadCredentialsException("У вас нет прав на редактирование этого комментария");
        }
        commentRepository.delete(commentEntity);
    }

    @Override
    public Comment updateComment(int adId, int commentId, CreateOrUpdateComment createOrUpdateComment,
                                 String username) {
        CommentEntity commentEntity = getCommentEntityFromDatabase(commentId, adId);
        if (!checkPermission(commentEntity, username)) {
            throw new BadCredentialsException("У вас нет прав на удаление этого комментария");
        }
        commentEntity.setText(createOrUpdateComment.getText());
        commentRepository.save(commentEntity);
        return commentMapper.commentEntityToComment(commentEntity);
    }

    private boolean checkPermission(CommentEntity commentEntity, String username) {
        UserEntity userEntity = commentEntity.getUser();
        UserEntity currentUserEntity = userRepository.findByUsername(username);
        return userEntity.getUsername().equals(currentUserEntity.getUsername()) ||
                currentUserEntity.getRole().name().equals("ADMIN");
    }

    private CommentEntity getCommentEntityFromDatabase(int commentId, int adId) {
        checkAdExistenceById(adId);
        CommentEntity commentEntity = commentRepository.findCommentEntityByIdAndAdId(commentId, adId);
        if (commentEntity == null) {
            throw new CommentNotFoundException();
        }
        return commentEntity;
    }

    public void checkAdExistenceById(int id) {
        try {
            if (!adsRepository.existsById(id)) {
                throw new AdNotFoundException("Объявление не найдено!");
            }
        } catch (AdNotFoundException e) {
            throw new AdNotFoundException("Объявление не найдено!");
        }
    }
}
