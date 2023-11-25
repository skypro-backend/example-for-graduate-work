package ru.skypro.homework.service;

import org.springframework.stereotype.Service;
import ru.skypro.homework.dto.AdDTO;
import ru.skypro.homework.dto.CommentDTO;
import ru.skypro.homework.dto.CreateOrUpdateAd;
import ru.skypro.homework.dto.CreateOrUpdateComment;
import ru.skypro.homework.mapper.CommentMapper;
import ru.skypro.homework.model.Ad;
import ru.skypro.homework.model.Comment;
import ru.skypro.homework.repository.AdRepository;
import ru.skypro.homework.repository.CommentRepository;

import java.text.SimpleDateFormat;
import java.time.Instant;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Date;
import java.util.List;

@Service
public class CommentService {
    private final CommentRepository commentRepository;
    private final AdRepository adRepository;
    private final CommentMapper commentMapping;

    public static String getCurrentTimeStamp() {
        SimpleDateFormat sdfDate = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss.SSS");
        Date now = new Date();
        String strDate = sdfDate.format(now);
        return strDate;
    }

    public CommentService(CommentRepository commentRepository, AdRepository adRepository, CommentMapper commentMapping) {
        this.commentRepository = commentRepository;
        this.adRepository = adRepository;
        this.commentMapping = commentMapping;
    }

    public void createComment(int id, CreateOrUpdateComment createOrUpdateComment) {
        Ad ad = adRepository.findByPk(id);
        Comment newComment = new Comment();
        newComment.setText(createOrUpdateComment.getText());
        newComment.setCreatedAt((int) Instant.now().toEpochMilli());
        commentRepository.save(newComment);
    }

    public Collection<CommentDTO> findByAd(int id) {
        List<Comment> commentList = commentRepository.findAllByAd_pk(id);
        List<CommentDTO> commentDTOList = new ArrayList<>(commentList.size());
        for (Comment c : commentList) {
            commentDTOList.add(commentMapping.mapToCommentDto(c));
        }
        return commentDTOList;
    }

    public void deleteComment(int adId, int commentId) {
//        List<Comment> commentList = commentRepository.findAllByAd_pk(adId);
//        for (Comment c : commentList) {
//            if (c.getPk() == commentId) {
//                adRepository.deleteById(commentId);
//            }
//        }
        commentRepository.deleteById(commentId);
    }

    public CommentDTO updateComment(int adId, int commentId, CreateOrUpdateComment createOrUpdateComment) {
        Comment updateComment = commentRepository.findByPk(commentId);
        updateComment.setText(createOrUpdateComment.getText());
        commentRepository.save(updateComment);
        return commentMapping.mapToCommentDto(updateComment);
    }
}
