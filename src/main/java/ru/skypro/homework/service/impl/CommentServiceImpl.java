package ru.skypro.homework.service.impl;

import lombok.RequiredArgsConstructor;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Service;
import ru.skypro.homework.dto.Comment;
import ru.skypro.homework.dto.Comments;
import ru.skypro.homework.dto.CreateOrUpdateComment;
import ru.skypro.homework.entity.AdEntity;
import ru.skypro.homework.entity.CommentEntity;
import ru.skypro.homework.entity.UserEntity;
import ru.skypro.homework.exception.AdIsNotFoundException;
import ru.skypro.homework.exception.CommentIsNotFoundException;
import ru.skypro.homework.exception.ForbiddenException;
import ru.skypro.homework.mapper.CommentMapper;
import ru.skypro.homework.repository.AdRepository;
import ru.skypro.homework.repository.CommentRepository;
import ru.skypro.homework.repository.UserRepository;
import ru.skypro.homework.service.CommentService;

import javax.transaction.Transactional;
import java.time.Instant;
import java.util.stream.Collectors;

/**
 * Service for addition, receipt, update and removal of a comment
 */
@Service
@RequiredArgsConstructor
public class CommentServiceImpl implements CommentService {
    private final AdRepository adRepository;
    private final CommentMapper commentMapper;
    private final UserRepository userRepository;
    private final CommentRepository commentRepository;

    /**
     * Gets comments of an ad
     * @param id of an ad
     * @return Comments DTO consisting of a list of comments and the size of the list
     */
    @Override
    public Comments getCommentsByAdId(int id) {
        if (adRepository.existsById(id)) {
            return new Comments(commentRepository.findByAd_pk(id).stream()
                    .map(commentMapper::commentToCommentDTO)
                    .collect(Collectors.toList()));
        } else throw new AdIsNotFoundException("Ad is not found");
    }

    /**
     * Adds comment to an ad
     * @param id of an ad
     * @param commentDetails is CreateOrUpdateComment DTO consisting of a text of an ad
     * @param userDetails contains details such as the user's username, password, authorities (roles), and additional attributes
     * @return Comment DTO consisting of author id, author image, author firstname, time when a comment was created, comment id and text of a comment
     */
    @Override
    public Comment addCommentToAd(int id, CreateOrUpdateComment commentDetails, UserDetails userDetails) {
        AdEntity adEntity = adRepository.findById(id).orElseThrow(() -> new AdIsNotFoundException("Ad is not found"));
        UserEntity userEntity = userRepository.findByEmail(userDetails.getUsername()).orElseThrow();

        CommentEntity commentEntity = CommentEntity.builder()
                .author(userEntity)
                .ad(adEntity)
                .createdAt(Instant.now())
                .text(commentDetails.getText()).build();

        commentRepository.save(commentEntity);

        return commentMapper.commentToCommentDTO(commentEntity);
    }

    /**
     * Deletes a comment
     * @param adId is ad id
     * @param commentId is comment id
     * @param userDetails contains details such as the user's username, password, authorities (roles), and additional attributes
     */
    @Override
    @Transactional
    public void deleteComment(int adId, int commentId, UserDetails userDetails) {
        CommentEntity commentEntity = commentRepository.findById(commentId).orElseThrow(() -> new CommentIsNotFoundException("Comment is not found"));
        checkAccess(userDetails, commentEntity);
        commentRepository.deleteByPkAndAd_Pk(commentId, adId);
    }

    /**
     * Updates a comment
     * @param adId is ad id
     * @param commentId is comment id
     * @param commentDetails is CreateOrUpdateComment DTO consisting of a text of an ad
     * @param userDetails contains details such as the user's username, password, authorities (roles), and additional attributes
     * @return Comment DTO consisting of author id, author image, author firstname, time when a comment was created, comment id and text of a comment
     */
    @Override
    public Comment updateComment(int adId, int commentId, CreateOrUpdateComment commentDetails, UserDetails userDetails) {
        CommentEntity commentEntity = commentRepository.findById(commentId).orElseThrow(() -> new CommentIsNotFoundException("Comment is not found"));
        checkAccess(userDetails, commentEntity);
        commentEntity.setText(commentDetails.getText());
        commentRepository.save(commentEntity);
        return commentMapper.commentToCommentDTO(commentEntity);
    }

    /**
     * Throws ForbiddenException if username from userDetails is not equal to email from commentEntity and the authority from userDetails is not admin
     * @param userDetails contains details such as the user's username, password, authorities (roles), and additional attributes
     * @param commentEntity contains comment id, time when a comment was created, text of a comment, UserEntity and AdEntity
     */
    private void checkAccess(UserDetails userDetails, CommentEntity commentEntity) {
        if (!userDetails.getAuthorities().contains(new SimpleGrantedAuthority("ROLE_ADMIN"))
                && !userDetails.getUsername().equals(commentEntity.getAuthor().getEmail())) {
            throw new ForbiddenException("Access is not allowed");
        }
    }
}
