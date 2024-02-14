package ru.skypro.homework.service.impl;

import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;
import ru.skypro.homework.dto.CommentDTO;
import ru.skypro.homework.dto.CreateOrUpdateComment;
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

    public CommentServiceImpl(CommentRepository commentRepository, AdRepository adRepository) {
        this.commentRepository = commentRepository;
        this.adRepository = adRepository;
    }

    @Override
    public List<CommentDTO> getComments(Long adId) {
        List<Comment> comments = commentRepository.findByAdId(adId);
        return comments.stream().map(comment -> new CommentDTO(
                comment.getId(),
                comment.getAuthor(),
                comment.getAuthorImage(),
                comment.getAuthorFirstName(),
                comment.getCreatedAt(),
                comment.getText()
        )).collect(Collectors.toList());
    }

    @Override
    public CommentDTO addComment(Long adId, CreateOrUpdateComment comment) {
        Ad ad = adRepository.findById(adId).orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND));
        Comment newComment = new Comment(
                null,
                comment.getText(),
                ad,
                null,
                null,
                null,
                null
        );
        commentRepository.save(newComment);
        return new CommentDTO(
                newComment.getId(),
                newComment.getAuthor(),
                newComment.getAuthorImage(),
                newComment.getAuthorFirstName(),
                newComment.getCreatedAt(),
                newComment.getText()
        );
    }

    @Override
    public void deleteComment(Long adId, Long commentId) {
        Ad ad = adRepository.findById(adId).orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND));
        Comment comment = commentRepository.findById(commentId).orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND));

        if (!Objects.equals(ad.getId(), comment.getAd().getId())) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST);
        }

        commentRepository.deleteById(commentId);
    }

    @Override
    public CommentDTO updateComment(Long adId, Long commentId, CreateOrUpdateComment comment) {
        Ad ad = adRepository.findById(adId).orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND));
        Comment commentToUpdate = commentRepository.findById(commentId).orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND));

        if (!Objects.equals(ad.getId(), commentToUpdate.getAd().getId())) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST);
        }

        commentToUpdate.setText(comment.getText());
        commentRepository.save(commentToUpdate);

        return new CommentDTO(
                commentToUpdate.getId(),
                commentToUpdate.getAuthor(),
                commentToUpdate.getAuthorImage(),
                commentToUpdate.getAuthorFirstName(),
                commentToUpdate.getCreatedAt(),
                commentToUpdate.getText()
        );
    }
}
