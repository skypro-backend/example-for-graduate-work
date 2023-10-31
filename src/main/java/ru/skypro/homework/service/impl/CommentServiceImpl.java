package ru.skypro.homework.service.impl;

import lombok.extern.slf4j.Slf4j;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import ru.skypro.homework.dto.Role;
import ru.skypro.homework.dto.comment.Comment;
import ru.skypro.homework.dto.comment.Comments;
import ru.skypro.homework.dto.comment.CreateOrUpdateComment;
import ru.skypro.homework.entity.AdEntity;
import ru.skypro.homework.entity.CommentEntity;
import ru.skypro.homework.entity.UserEntity;
import ru.skypro.homework.repository.AdRepository;
import ru.skypro.homework.repository.CommentRepository;
import ru.skypro.homework.repository.UserRepository;
import ru.skypro.homework.service.CommentMapper;
import ru.skypro.homework.service.CommentService;

import java.time.LocalDate;
import java.util.List;

@Slf4j
@Service
public class CommentServiceImpl  implements CommentService {

    private final CommentRepository commentRepository;
    private final CommentMapper commentMapper;
    private final AdRepository adRepository;
    private final UserRepository userRepository;

    private final UserDetails userDetails;

    public static final String USER_NOT_FOUND = "User not found";


    public CommentServiceImpl(CommentRepository commentRepository, CommentMapper commentMapper, AdRepository adRepository, UserRepository userRepository, UserDetails userDetails) {
        this.commentRepository = commentRepository;
        this.commentMapper = commentMapper;
        this.adRepository = adRepository;
        this.userRepository = userRepository;
        this.userDetails = userDetails;
    }

    @Override
    @Transactional(readOnly = true)
    public Comments getComments(Integer id) {
        List<CommentEntity> commentEntityList = commentRepository
                .findCommentEntitiesByAdEntity_Id(id);
        return commentMapper.toComments(commentEntityList);
    }
    @Override
    @Transactional
    public Comment addComment(Integer id, CreateOrUpdateComment createOrUpdateComment) {
        AdEntity adEntity = adRepository.findById(id).orElseThrow(() -> new IllegalArgumentException("Объявление не найдено"));
        CommentEntity commentEntity = commentMapper.toCommentEntity(createOrUpdateComment, new CommentEntity());
        UserEntity userEntity = userRepository.findByEmail(userDetails.getUsername())
                .orElseThrow(() -> new UsernameNotFoundException(USER_NOT_FOUND));
        commentEntity.setUserEntity(userEntity);
        commentEntity.setAdEntity(adEntity);
        log.info("Коммент был создан в БД" + LocalDate.now());
        return commentMapper.toComment(commentRepository.save(commentEntity));
    }
    @Override
    @Transactional
    public boolean deleteComment(Integer adId, Integer commentId) {
        String userName = userDetails.getUsername();
        UserEntity userEntity = userRepository.findByEmail(userName)
                .orElseThrow(() -> new UsernameNotFoundException(USER_NOT_FOUND));
        CommentEntity commentEntity = commentRepository.findByIdAndAdEntity_Id(commentId, adId)
                .orElseThrow(() -> new IllegalArgumentException("Comment not found"));
        if (userEntity.getRole() == Role.ADMIN || commentEntity.getUserEntity().equals(userEntity)) {
            commentRepository.delete(commentEntity);
            log.info("Comment was removed from database. " + LocalDate.now());
            return true;
        }
        log.info("Невозможно удалить коммент из БД " +
                "Возможно такого коммента нет" + LocalDate.now());
        return false;
    }
    @Override
    public Comment updateComment(Integer adId, Integer commentId, CreateOrUpdateComment createOrUpdateComment) {
        String userName = userDetails.getUsername();
        UserEntity userEntity = userRepository.findByEmail(userName)
                .orElseThrow(() -> new UsernameNotFoundException(USER_NOT_FOUND));
        CommentEntity commentEntity = commentRepository.findByIdAndAdEntity_Id(commentId, adId)
                .orElseThrow(() -> new IllegalArgumentException("Comment not found"));
        if (userEntity.getRole() == Role.ADMIN || commentEntity.getUserEntity().equals(userEntity)) {
            log.info("Comment successfully updated." + LocalDate.now());
            return commentMapper.toComment(commentRepository.save(
                    commentMapper.toCommentEntity(createOrUpdateComment, commentEntity)));
        }
        log.info("It is not possible to update a comment in database. " + LocalDate.now());
        return null;
    }
}
