package ru.skypro.homework.service.impl;

import org.springframework.security.access.AccessDeniedException;
import org.springframework.security.core.Authentication;
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
import ru.skypro.homework.service.CommentService;

import java.util.ArrayList;
import java.util.List;

@Service
public class CommentServiceImpl implements CommentService {

    private final UserRepository userRepository;
    private final AdRepository adRepository;
    private final CommentRepository commentRepository;
    private final CommentMapper commentMapper;

    public CommentServiceImpl(UserRepository userRepository, AdRepository adRepository, CommentRepository commentRepository, CommentMapper commentMapper) {
        this.userRepository = userRepository;
        this.adRepository = adRepository;
        this.commentRepository = commentRepository;
        this.commentMapper = commentMapper;
    }

    @Override
    public Comment createComment(Long adId, CreateOrUpdateComment dto, Authentication authentication) {
        AdEntity adEntity = adRepository.findById(adId)
                .orElseThrow(() -> new RuntimeException("Объявление с id: " + adId + " не найдено"));
        CommentEntity commentEntity = commentMapper.toComment(dto);
        commentEntity.setAd(adEntity);
        String email = authentication.getName();
        UserEntity userEntity = userRepository.findByEmail(email)
                .orElseThrow(() -> new RuntimeException("Пользователь с email: " + email + " не найден"));
        commentEntity.setAuthor(userEntity);
        CommentEntity comment = commentRepository.save(commentEntity);
        return commentMapper.toCommentDto(comment);
    }


    @Override
    public Comments getCommentsByAdId(Long adId) {
        List<CommentEntity> commentsEntity = commentRepository.findByAdId(adId);
        List<Comment> commentsDto = new ArrayList<>();
        for (CommentEntity comment : commentsEntity) {
            commentsDto.add(commentMapper.toCommentDto(comment));
        }
        int count = commentsDto.size();
        return new Comments(count, commentsDto);

    }

    @Override
    public Comment updateComment(Long commentId, CreateOrUpdateComment dto, Authentication authentication) {
        CommentEntity commentEntity = commentRepository.findById(commentId)
                .orElseThrow(() -> new RuntimeException("Комментарий с id: " + commentId + " не найден"));
        String email = authentication.getName();
        UserEntity userEntity = userRepository.findByEmail(email)
                .orElseThrow(() -> new RuntimeException("Пользователь не найден"));
        boolean isAuthor = commentEntity.getAuthor().getId().equals(userEntity.getId());
        boolean isAdmin = userEntity.getRole() == Role.ADMIN;
        CommentEntity savedComment;
        if (isAuthor || isAdmin) {
            commentMapper.toUpdateCommentFromDto(commentEntity, dto);
            savedComment = commentRepository.save(commentEntity);
        } else {
            throw new AccessDeniedException("Недостаточно прав для изменения этого объявления");
        }
        return commentMapper.toCommentDto(savedComment);
    }


    @Override
    public void deleteComment(Long commentId, Authentication authentication) {
        CommentEntity commentEntity = commentRepository.findById(commentId)
                .orElseThrow(() -> new RuntimeException("Комментарий с id: " + commentId + " не найден"));
        String email = authentication.getName();
        UserEntity userEntity = userRepository.findByEmail(email)
                .orElseThrow(() -> new RuntimeException("Пользователь не найден"));
        boolean isAuthor = commentEntity.getAuthor().getId().equals(userEntity.getId());
        boolean isAdmin = userEntity.getRole() == Role.ADMIN;
        if (isAuthor || isAdmin) {
            commentRepository.deleteById(commentId);
        } else {
            throw new AccessDeniedException("Недостаточно прав для удаления этого объявления");
        }
    }


}
