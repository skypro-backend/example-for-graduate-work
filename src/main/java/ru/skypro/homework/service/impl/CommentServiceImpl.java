package ru.skypro.homework.service.impl;

import lombok.RequiredArgsConstructor;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Service;
import ru.skypro.homework.dto.Comment;
import ru.skypro.homework.dto.Comments;
import ru.skypro.homework.dto.CreateOrUpdateComment;
import ru.skypro.homework.entity.AdEntity;
import ru.skypro.homework.entity.CommentEntity;
import ru.skypro.homework.entity.UserEntity;
import ru.skypro.homework.mapper.CommentMapper;
import ru.skypro.homework.repository.AdRepository;
import ru.skypro.homework.repository.CommentRepository;
import ru.skypro.homework.service.CommentsService;
import ru.skypro.homework.service.UsersService;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class CommentServiceImpl implements CommentsService {

    private final CommentRepository commentRepository;
    private final UsersService userService;
    private final AdRepository adsRepository;

    @Override
    public List<Comment> listCommentsAdById(Long id) {
        List<CommentEntity> commentList = commentRepository.findAllByAdId(id);
        List<Comment> commentDtoList = new ArrayList<>();
        for (CommentEntity comment : commentList) {
            commentDtoList.add(CommentMapper.INSTANCE.commentEntityToComment(comment));
        }
        return commentDtoList;
    }

    @Override
    public Optional<Comment> createComment(String userLogin, Long id, CreateOrUpdateComment createComment) {
        Optional<UserEntity> optionalUser = userService.getUser(userLogin);
        if (optionalUser.isEmpty()) {
            return Optional.empty();
        }
        Optional<AdEntity> optionalAd = adsRepository.findById(id);
        if (optionalAd.isEmpty()) {
            return Optional.empty();
        }
        UserEntity userEntity = optionalUser.get();
        AdEntity adEntity = optionalAd.get();
        CommentEntity comment = new CommentEntity();
        comment.setUserEntity(userEntity);
        comment.setAdEntity(adEntity);
        comment.setCreatedAt(System.currentTimeMillis());
        comment.setText(createComment.getText());
        commentRepository.save(comment);
        return Optional.ofNullable(CommentMapper.INSTANCE.commentEntityToComment(comment));
    }

    @Override
    public Comments listCommentsAdById(int id) {
        return null;
    }

    @Override
    @PreAuthorize("hasRole('ADMIN')")
    public boolean deleteById(String userLogin, Long adId, Long commentId) {
        if (!adsRepository.existsById(adId)) {
            return false;
        }
        Optional<CommentEntity> optionalComment = commentRepository.findById(commentId);

        if (optionalComment.isEmpty()) {
            return false;
        }
        commentRepository.deleteById(commentId);
        return true;
    }

    @Override
    @PreAuthorize("hasRole('ADMIN')")
    public Optional<Comment> editComment(String userLogin, Long adId, Long commentId, CreateOrUpdateComment updateComment) {
        if (!adsRepository.existsById(adId)) {
            return Optional.empty();
        }
        Optional<CommentEntity> optionalComment = commentRepository.findById(commentId);
        if (optionalComment.isEmpty()) {
            return Optional.empty();
        }
        CommentEntity commentEntity = optionalComment.get();
        commentEntity.setText(updateComment.getText());
        commentRepository.save(commentEntity);
        return Optional.ofNullable(CommentMapper.INSTANCE.commentEntityToComment(commentEntity));
    }

}