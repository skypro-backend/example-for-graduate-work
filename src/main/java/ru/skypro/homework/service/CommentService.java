package ru.skypro.homework.service;

import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Service;
import ru.skypro.homework.dto.*;
import ru.skypro.homework.mapper.CommentMapper;
import ru.skypro.homework.model.Ad;
import ru.skypro.homework.model.Comment;
import ru.skypro.homework.model.User;
import ru.skypro.homework.repository.AdRepository;
import ru.skypro.homework.repository.CommentRepository;

import java.io.IOException;
import java.text.SimpleDateFormat;
import java.time.Instant;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@Service
public class CommentService {
    private final CommentRepository commentRepository;
    private final AdRepository adRepository;
    private final CommentMapper commentMapping;
    private final UserService userService;

    public CommentService(CommentRepository commentRepository, AdRepository adRepository, CommentMapper commentMapping, UserService userService) {
        this.commentRepository = commentRepository;
        this.adRepository = adRepository;
        this.commentMapping = commentMapping;
        this.userService = userService;
    }

    /**
     * Добавление комментария к объявлению.
     */
    public CommentDTO createComment(int id, CreateOrUpdateComment createOrUpdateComment, Authentication authentication) {
        Ad ad = adRepository.findByPk(id);
        User author = userService.loadUserByUsername(authentication.getName());
        Comment newComment = new Comment();
        newComment.setAd(ad);
        newComment.setText(createOrUpdateComment.getText());
        newComment.setCreatedAt(Instant.now().toEpochMilli());
        newComment.setUser(author);
        commentRepository.save(newComment);
        return commentMapping.mapToCommentDto(newComment);
    }

    /**
     * Получение комментариев объявления.
     */
    public CommentsDTO findByAd(int id) {
        List<Comment> commentList = commentRepository.findAllByAd_pk(id);
        List<CommentDTO> commentDTOList = new ArrayList<>(commentList.size());
        for (Comment c : commentList) {
            commentDTOList.add(commentMapping.mapToCommentDto(c));
        }
        CommentsDTO dto = new CommentsDTO();
        dto.setCount(commentList.size());
        dto.setResults(commentDTOList);
        return dto;
    }

    /**
     * Удаление комментария.
     */

    public boolean deleteComment(int adId, int commentId, Authentication authentication) throws IOException {
        User author = userService.loadUserByUsername(authentication.getName());
        Comment comment = commentRepository.findByAdPkAndPk(adId, commentId);
        if (author.equals(comment.getUser()) || author.getRole() == RoleDTO.ADMIN) {
            commentRepository.delete(comment);
            return true;
        } else {
            return false;
        }
    }

    /**
     * Обновление комментария.
     */
    public CommentDTO updateComment(int adId, int commentId, CreateOrUpdateComment
            createOrUpdateComment, Authentication authentication) {
        User author = userService.loadUserByUsername(authentication.getName());
        Comment updateComment = commentRepository.findByPk(commentId);
        if (updateComment.getAd().getPk() == adId) {
            if (author.equals(updateComment.getUser()) || author.getRole() == RoleDTO.ADMIN) {
                updateComment.setText(createOrUpdateComment.getText());
                updateComment.setCreatedAt(Instant.now().toEpochMilli());
                commentRepository.save(updateComment);
            }
        }
        return commentMapping.mapToCommentDto(updateComment);
    }

}


