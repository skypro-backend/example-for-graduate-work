package ru.skypro.homework.service.comment;

import lombok.RequiredArgsConstructor;
import ru.skypro.homework.entity.Ad;
import ru.skypro.homework.entity.Comment;
import org.springframework.stereotype.Service;
import ru.skypro.homework.dto.CommentDTO;
import ru.skypro.homework.mapper.CommentMapper;
import ru.skypro.homework.projection.Comments;
import ru.skypro.homework.projection.CreateOrUpdateComment;
import ru.skypro.homework.repository.AdRepository;
import ru.skypro.homework.repository.CommentRepository;
import ru.skypro.homework.exception.AdsNotFound;

import java.time.Instant;
import java.util.List;


@RequiredArgsConstructor
@Service
public class CommentServiceImpl implements CommentService{
    private final CommentRepository commentRepository;
    private final AdRepository adRepository;


    @Override
    public List<Comments> getAllCommentsByAdId(Integer id) {
        return commentRepository.getAllCommentsByAdId(id);
    }

    @Override
    public CommentDTO createComment(Integer id, CreateOrUpdateComment comment) {
        Ad ad = adRepository.findById(id).orElseThrow(AdsNotFound::new);
        return CommentMapper.fromComment(commentRepository.save(
                new Comment().setText(comment.getText()).setAd(ad)));
    }

    @Override
    public void deleteComment(Integer commentId,Integer adId) {
        commentRepository.deleteCommentByPkAndAd_Pk(commentId, adId);
    }

    @Override
    public CommentDTO updateComment(Integer commentId,Integer adId, CreateOrUpdateComment comment) {
        Comment commentResult = commentRepository.findByPkAndAd_Pk(commentId, adId);
        commentResult.setText(comment.getText()).setCreatedAt(Instant.now());
        return CommentMapper.fromComment(commentRepository.save(commentResult));
    }
}