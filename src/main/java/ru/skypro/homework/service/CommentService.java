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
    public static String getCurrentTimeStamp() {
        SimpleDateFormat sdfDate = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss.SSS");
        Date now = new Date();
        String strDate = sdfDate.format(now);
        return strDate;
    }

    public CommentService(CommentRepository commentRepository, AdRepository adRepository, CommentMapper commentMapping, UserService userService) {
        this.commentRepository = commentRepository;
        this.adRepository = adRepository;
        this.commentMapping = commentMapping;
        this.userService = userService;
    }

    public CommentDTO createComment(int id, CreateOrUpdateComment createOrUpdateComment, Authentication authentication) {
        Ad ad = adRepository.findByPk(id);
        User author = userService.loadUserByUsername(authentication.getName());
        Comment newComment = new Comment();
        newComment.setAd(ad);
        newComment.setText(createOrUpdateComment.getText());
        newComment.setCreatedAt((int) Instant.now().toEpochMilli());
        newComment.setUser(author);
        commentRepository.save(newComment);
        return commentMapping.mapToCommentDto(newComment);
    }

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


    public void deleteComment(int adId, int commentId) {
        Comment comment = commentRepository.findByPk(commentId);
        if (comment.getAd().getPk() == adId) {
            commentRepository.delete(comment);
        }

    }

    public CommentDTO updateComment(int adId, int commentId, CreateOrUpdateComment createOrUpdateComment) {
        Comment updateComment = commentRepository.findByPk(commentId);
        if (updateComment.getAd().getPk() == adId) {
            updateComment.setText(createOrUpdateComment.getText());


            commentRepository.save(updateComment);

        }
        return commentMapping.mapToCommentDto(updateComment);
    }
}

