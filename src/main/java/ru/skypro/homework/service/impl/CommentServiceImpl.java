package ru.skypro.homework.service.impl;

import org.springframework.security.access.AccessDeniedException;
import org.springframework.security.access.prepost.PostAuthorize;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;

import org.springframework.transaction.annotation.Transactional;
import ru.skypro.homework.dto.Comment;
import ru.skypro.homework.dto.Comments;
import ru.skypro.homework.dto.CreateOrUpdateComment;

import ru.skypro.homework.dto.Role;
import ru.skypro.homework.model.AdEntity;
import ru.skypro.homework.model.CommentEntity;
import ru.skypro.homework.repo.AdRepository;
import ru.skypro.homework.repo.CommentRepository;
import ru.skypro.homework.repo.UserRepo;
import ru.skypro.homework.service.CommentMapper;
import ru.skypro.homework.service.CommentService;
import ru.skypro.homework.util.exceptions.NotFoundException;

import java.time.LocalDateTime;
import java.time.ZoneOffset;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class CommentServiceImpl implements CommentService {
    private final CommentRepository commentRepository;
    private final AdRepository adRepository;
    private final CommentMapper commentMapper;
    private final UserRepo userRepo;

    public CommentServiceImpl(CommentRepository commentRepository, AdRepository adRepository, CommentMapper commentMapper, UserRepo userRepo) {
        this.commentRepository = commentRepository;
        this.adRepository = adRepository;
        this.commentMapper = commentMapper;
        this.userRepo = userRepo;
    }

    @Override
    public Comments getComments(Integer id) {
        List<CommentEntity> commentList = commentRepository.findAllByAdId(id);
        return commentMapper.commentsToListDTO(commentList);
    }

    @Override
    public Comment createComment(CreateOrUpdateComment createOrUpdateComment, Integer id) {
        LocalDateTime now = LocalDateTime.now();
        long milliseconds = now.toInstant(ZoneOffset.UTC).toEpochMilli();
        CommentEntity commentEntity = new CommentEntity();
        AdEntity adEntity = adRepository.findById(id).orElse(null);
        commentEntity.setAuthor(adEntity.getAuthor());
        commentEntity.setCreatedAt(milliseconds);
        commentEntity.setText(createOrUpdateComment.getText());
        commentEntity.setAd(adEntity);
        commentRepository.save(commentEntity);
        Comment comment = commentMapper.commentEntityToCommentDTO(commentEntity);
        return comment;
    }

    @Override
    @Transactional
    public void deleteComment(Integer adId, Integer commentId) {
        CommentEntity comment = commentRepository.findById(commentId).orElseThrow(() -> new NotFoundException("Комментарий с ID: "+ commentId+" не найден"));
        if(comment.getAuthor().getRole().equals(Role.ADMIN) || comment.getAuthor().getLogin().equals(getMe())){
        commentRepository.deleteByAdIdAndId(adId,commentId);
        }
        else throw new AccessDeniedException("Нет доступа");
    }

    @Override
    @PostAuthorize("returnObject.author == principal.username or hasRole('ADMIN')")
    public Comment updateComment(Integer adId, Integer commentId, CreateOrUpdateComment createOrUpdateComment) {
        LocalDateTime now = LocalDateTime.now();
        long milliseconds = now.toInstant(ZoneOffset.UTC).toEpochMilli();
        CommentEntity commentEntity = commentRepository.findByAdIdAndId(adId,commentId);
        commentEntity.setCreatedAt(milliseconds);
        commentEntity.setText(createOrUpdateComment.getText());
        commentRepository.save(commentEntity);
        Comment comment = commentMapper.commentEntityToCommentDTO(commentEntity);
        return comment;
    }

    private String getMe(){
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        return authentication.getName();
    }

}
