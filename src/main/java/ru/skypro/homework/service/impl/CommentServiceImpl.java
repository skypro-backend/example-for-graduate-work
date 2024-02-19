package ru.skypro.homework.service.impl;

import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;
import ru.skypro.homework.dto.CommentDTO;
import ru.skypro.homework.dto.CreateOrUpdateComment;
import ru.skypro.homework.mapper.CommentMapper;
import ru.skypro.homework.model.Ad;
import ru.skypro.homework.model.Comment;
import ru.skypro.homework.repository.AdRepository;
import ru.skypro.homework.repository.CommentRepository;
import ru.skypro.homework.service.CommentService;

import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;

@Service
public class CommentServiceImpl implements CommentService {
    private final CommentRepository commentRepository;
    private final AdRepository adRepository;
    private final CommentMapper commentMapper;

    public CommentServiceImpl(CommentRepository commentRepository, AdRepository adRepository, CommentMapper commentMapper) {
        this.commentRepository = commentRepository;
        this.adRepository = adRepository;
        this.commentMapper = commentMapper;
    }

    @Override
    public List<CommentDTO> getComments(Long adId) {
        Ad ad = adRepository.findById(adId).orElseThrow(() -> new RuntimeException("Ad was not found"));
        List<Comment> comments = ad.getComments();
        return comments.stream()
                .map(commentMapper::toCommentDTO)
                .collect(Collectors.toList());
    }

    @Override
    public CommentDTO addComment(Long adId, CreateOrUpdateComment comment) {
        Ad ad = adRepository.findById(adId).orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND));
        Comment newComment = new Comment();
        newComment.setText(comment.getText());
        newComment.setAd(ad);
        commentRepository.save(newComment);
        return commentMapper.toCommentDTO(newComment);
    }

    @Override
    public void deleteComment(Long adId, Long commentId) {
        Ad ad = adRepository.findById(adId).orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND));
        Comment comment = commentRepository.findById(commentId).orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND));

        if (comment.getAd() == null || !Objects.equals(ad.getId(), comment.getAd().getId())) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST);
        }

        commentRepository.deleteById(commentId);
    }

    @Override
    public CommentDTO updateComment(Long adId, Long commentId, CreateOrUpdateComment comment) {
        Ad ad = adRepository.findById(adId).orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND));
        Comment commentToUpdate = commentRepository.findById(commentId).orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND));

        if (commentToUpdate.getAd() == null || !Objects.equals(ad.getId(), commentToUpdate.getAd().getId())) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST);
        }

        commentToUpdate.setText(comment.getText());
        commentRepository.save(commentToUpdate);

        return commentMapper.toCommentDTO(commentToUpdate);
    }
}
