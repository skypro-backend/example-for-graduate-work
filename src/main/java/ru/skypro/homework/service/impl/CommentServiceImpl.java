package ru.skypro.homework.service.impl;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import ru.skypro.homework.dto.CommentDTO;
import ru.skypro.homework.dto.GetCommentDTO;
import ru.skypro.homework.model.Ads;
import ru.skypro.homework.model.Comment;
import ru.skypro.homework.repository.AdsRepository;
import ru.skypro.homework.repository.CommentRepository;
import ru.skypro.homework.service.CommentService;

import java.util.ArrayList;
import java.util.List;

@Service
@RequiredArgsConstructor
public class CommentServiceImpl implements CommentService {

    private final CommentRepository commentRepository;
    private final AdsRepository adsRepository;
    @Override
    public List<CommentDTO> getCommentsByAdsId(long id) {
        Ads ads = adsRepository.findById(id).orElse(null);
        List<Comment> commentList = ads.getComments();
        List<CommentDTO> commentDTOList = new ArrayList<>();
        for (Comment comment:commentList){
            commentDTOList.add(Comment.mapToCommentDto(comment));
        }
        return commentDTOList;
    }

    @Override
    public CommentDTO addComment(long adsId, String text) {
        Ads ads = adsRepository.findById(adsId).orElse(null);
        Comment comment = new Comment();
        comment.setText(text);
        comment.setAds(ads);
        return Comment.mapToCommentDto(comment);
    }

    @Override
    public void deleteComment(long adsId, long commentId) {

        commentRepository.deleteById(commentId);

    }

    @Override
    public CommentDTO updateComment(long adsId, long commentId, String text) {
        Ads ads = adsRepository.findById(adsId).orElse(null);
        Comment comment = new Comment();
        comment.setText(text);
        comment.setAds(ads);
        return Comment.mapToCommentDto(comment);
    }
}
