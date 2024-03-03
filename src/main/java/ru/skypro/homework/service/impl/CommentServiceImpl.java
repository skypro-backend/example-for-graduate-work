package ru.skypro.homework.service.impl;

import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;
import ru.skypro.homework.dto.CreateOrUpdateComment;
import ru.skypro.homework.dto.Role;
import ru.skypro.homework.exception.NotEnoughPermissionsException;
import ru.skypro.homework.exception.ResourceNotFoundException;
import ru.skypro.homework.mapper.CommentMapper;
import ru.skypro.homework.model.Ad;
import ru.skypro.homework.model.Comment;
import ru.skypro.homework.repository.AdRepository;
import ru.skypro.homework.repository.CommentRepository;
import ru.skypro.homework.security.CustomUserDetails;
import ru.skypro.homework.service.CommentService;
import ru.skypro.homework.util.SecurityUtil;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Objects;

@Service
@AllArgsConstructor
public class CommentServiceImpl implements CommentService {
    private final CommentRepository commentRepository;
    private final AdRepository adRepository;
    private final CommentMapper commentMapper;

    @Override
    public List<Comment> getComments(Long adId) {
        Ad ad = adRepository.findById(adId).orElseThrow(() -> new ResourceNotFoundException(String.format("Ad with id %d not found", adId)));
        return ad.getComments();
    }

    @Override
    public Comment addComment(Long adId, CreateOrUpdateComment dto) {
        Ad ad = adRepository.findById(adId).orElseThrow(() -> new ResourceNotFoundException(String.format("Ad with id %d not found", adId)));
        Comment comment = commentMapper.toComment(dto);
        comment.setAd(ad);
        comment.setAuthor(SecurityUtil.getUserDetails().getUser());
        comment.setCreatedAt(LocalDateTime.now());
        return commentRepository.save(comment);
    }

    @Override
    public void deleteComment(Long adId, Long commentId) {
        Ad ad = adRepository.findById(adId).orElseThrow(() -> new ResourceNotFoundException(String.format("Ad with id %d not found", adId)));
        Comment comment = commentRepository.findById(commentId).orElseThrow(() -> new ResourceNotFoundException(String.format("Comment with id %d not found", commentId)));
        checkPermissions(comment);
        if (comment.getAd() == null || !Objects.equals(ad.getId(), comment.getAd().getId())) {
            throw new IllegalArgumentException(String.format("Ad with id %d has no comment with id %d", adId, commentId));
        }

        commentRepository.deleteById(commentId);
    }

    @Override
    public Comment updateComment(Long adId, Long commentId, CreateOrUpdateComment comment) {
        Ad ad = adRepository.findById(adId).orElseThrow(() -> new ResourceNotFoundException(String.format("Ad with id %d not found", adId)));
        Comment commentToUpdate = commentRepository.findById(commentId).orElseThrow(() -> new ResourceNotFoundException(String.format("Comment with id %d not found", commentId)));
        checkPermissions(commentToUpdate);
        if (commentToUpdate.getAd() == null || !Objects.equals(ad.getId(), commentToUpdate.getAd().getId())) {
            throw new IllegalArgumentException(String.format("Ad with id %d has no comment with id %d", adId, commentId));
        }

        commentToUpdate.setText(comment.getText());
        return commentRepository.save(commentToUpdate);
    }

    private void checkPermissions(Comment comment) {
        CustomUserDetails userDetails = SecurityUtil.getUserDetails();

        if (!Objects.equals(userDetails.getUser(), comment.getAuthor()) && !userDetails.getAuthorities().contains(Role.ADMIN)) {
            throw new NotEnoughPermissionsException("You don't have enough rights");
        }
    }
}
