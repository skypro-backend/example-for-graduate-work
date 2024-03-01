package ru.skypro.homework.service.impl;

import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;
import ru.skypro.homework.dto.CreateOrUpdateComment;
import ru.skypro.homework.dto.Role;
import ru.skypro.homework.model.Ad;
import ru.skypro.homework.model.Comment;
import ru.skypro.homework.repository.AdRepository;
import ru.skypro.homework.repository.CommentRepository;
import ru.skypro.homework.security.CustomUserDetails;
import ru.skypro.homework.service.CommentService;
import ru.skypro.homework.util.SecurityUtil;

import java.util.List;
import java.util.Objects;

@Service
@AllArgsConstructor
public class CommentServiceImpl implements CommentService {
    private final CommentRepository commentRepository;
    private final AdRepository adRepository;

    @Override
    public List<Comment> getComments(Long adId) {
        Ad ad = adRepository.findById(adId).orElseThrow(() -> new RuntimeException("Ad was not found"));
        return ad.getComments();
    }

    @Override
    public Comment addComment(Long adId, CreateOrUpdateComment comment) {
        Ad ad = adRepository.findById(adId).orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND));
        Comment newComment = new Comment();
        newComment.setText(comment.getText());
        newComment.setAd(ad);
        return commentRepository.save(newComment);
    }

    @Override
    public void deleteComment(Long adId, Long commentId) {
        Ad ad = adRepository.findById(adId).orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND));
        Comment comment = commentRepository.findById(commentId).orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND));
        checkPermissions(comment);
        if (comment.getAd() == null || !Objects.equals(ad.getId(), comment.getAd().getId())) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST);
        }

        commentRepository.deleteById(commentId);
    }

    @Override
    public Comment updateComment(Long adId, Long commentId, CreateOrUpdateComment comment) {
        Ad ad = adRepository.findById(adId).orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND));
        Comment commentToUpdate = commentRepository.findById(commentId).orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND));
        checkPermissions(commentToUpdate);
        if (commentToUpdate.getAd() == null || !Objects.equals(ad.getId(), commentToUpdate.getAd().getId())) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST);
        }

        commentToUpdate.setText(comment.getText());
        return commentRepository.save(commentToUpdate);
    }

    private void checkPermissions(Comment comment) {
        CustomUserDetails userDetails = SecurityUtil.getUserDetails();

        if (!Objects.equals(userDetails.getUser(), comment.getAuthor()) && !userDetails.getAuthorities().contains(Role.ADMIN)) {
            throw new RuntimeException("Permissions error");
        }
    }
}
