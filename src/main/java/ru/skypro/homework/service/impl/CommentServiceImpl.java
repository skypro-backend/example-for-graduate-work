package ru.skypro.homework.service.impl;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import ru.skypro.homework.dto.CommentDTO;
import ru.skypro.homework.dto.CommentsDTO;
import ru.skypro.homework.dto.CreateOrUpdateCommentDTO;
import ru.skypro.homework.entity.Ad;
import ru.skypro.homework.entity.Comment;
import ru.skypro.homework.exception.AdNotFoundException;
import ru.skypro.homework.exception.CommentNotFoundException;
import ru.skypro.homework.exception.ForbiddenAccessException;
import ru.skypro.homework.mapper.CommentMapper;
import ru.skypro.homework.repository.AdRepository;
import ru.skypro.homework.repository.CommentRepository;
import ru.skypro.homework.service.CommentService;
import ru.skypro.homework.service.UserService;
import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class CommentServiceImpl implements CommentService {

    private final CommentRepository commentRepository;
    private final CommentMapper commentMapper;
    private final UserService userService;
    private final AdServiceImpl adService;
    private final AdRepository adRepository;

    @Override
    public CommentDTO addComment(Integer adId, CreateOrUpdateCommentDTO comment) {
        Ad ad = adRepository.findById(adId).orElseThrow(AdNotFoundException::new);
        Comment newComment = new Comment();
        newComment.setAuthor(userService.findAuthUser().orElseThrow());
        newComment.setAd(ad);
        newComment.setCreatedAt(newComment.getCreatedAt());
        newComment.setText(comment.getText());
        commentRepository.save(newComment);
        return commentMapper.mapCommentToCommentDTO(newComment);
    }

    @Override
    public CommentDTO updateComment(Integer adId, Integer commentId, CreateOrUpdateCommentDTO commentDTO) {
        Comment updateComment = commentRepository.findById(commentId)
                .orElseThrow(() -> new CommentNotFoundException("Comment not found"));
        if (!adId.equals(updateComment.getAd().getId())) {
            throw new CommentNotFoundException("Comment not found");
        }
        try {
            adService.checkAccess(adId);
        } catch (ForbiddenAccessException ex) {
            throw new ForbiddenAccessException("Access denied for updating comment");
        }
        updateComment.setText(commentDTO.getText());
        commentRepository.save(updateComment);
        return commentMapper.mapCommentToCommentDTO(updateComment);
    }

    @Override
    public void deleteComment(Integer adId, Integer commentId) {
        Comment comment = commentRepository.findById(commentId)
                .orElseThrow(() -> new CommentNotFoundException("Comment not found"));
        if (!adId.equals(comment.getAd().getId())) {
            throw new CommentNotFoundException("Comment not found");
        }
        try {
            adService.checkAccess(adId);
        } catch (ForbiddenAccessException ex) {
            throw new ForbiddenAccessException("Access denied for deleting comment");
        }
        commentRepository.delete(comment);
    }

    @Override
    public CommentsDTO getComments(Integer adId) {
        List<CommentDTO> comments = commentRepository.getCommentsByAdId(adId)
                .stream()
                .map(commentMapper::mapCommentToCommentDTO)
                .collect(Collectors.toList());
        return commentMapper.mapListCommentDTOtoCommentsDTO(comments);
    }
}
