package ru.skypro.homework.service.impl;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import ru.skypro.homework.dto.CommentDTO;
import ru.skypro.homework.model.Ads;
import ru.skypro.homework.model.Comment;
import ru.skypro.homework.model.UserInfo;
import ru.skypro.homework.repository.AdsRepository;
import ru.skypro.homework.repository.CommentRepository;
import ru.skypro.homework.service.CommentService;
import ru.skypro.homework.service.mapper.CommentMapper;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@Service
@RequiredArgsConstructor
public class CommentServiceImpl implements CommentService {

    private final CommentRepository commentRepository;
    private final AdsRepository adsRepository;
    private final CommentMapper commentMapper;
    private final AuthServiceImpl authService;
    @Override
    public List<CommentDTO> getCommentsByAdsId(long id) {
        Ads ads = adsRepository.findById(id).orElse(null);
        List<Comment> commentList = ads.getComments();
        List<CommentDTO> commentDTOList = new ArrayList<>();
        for (Comment comment:commentList){
            commentDTOList.add(commentMapper.commentToCommentDto(comment));
        }
        return commentDTOList;
    }

    @Override
    public CommentDTO addComment(long adsId, String text) {
        UserInfo user = authService.getCurrentUser();
        Ads ads = adsRepository.findById(adsId).orElse(null);
        Comment comment = new Comment();
        comment.setText(text);
        comment.setAds(ads);
        comment.setAuthor(user);
        comment.setCreatedAt(String.valueOf(LocalDateTime.now()));;
        commentRepository.save(comment);

        return commentMapper.commentToCommentDto(comment);
    }

    @Override
    public void deleteComment(long adsId, long commentId) {

        commentRepository.deleteById(commentId);

    }

    @Override
    public CommentDTO updateComment(long adsId, long commentId, String text) {

        Comment comment = commentRepository.findById(commentId).orElse(null);
        comment.setText(text);
        comment.setCreatedAt(String.valueOf(LocalDateTime.now()));;
        commentRepository.save(comment);

        return commentMapper.commentToCommentDto(comment);
    }
}
