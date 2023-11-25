package ru.skypro.homework.service.impl;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import ru.skypro.homework.dto.CommentDTO;
import ru.skypro.homework.dto.Role;
import ru.skypro.homework.exception.UserNotRegisteredException;
import ru.skypro.homework.model.Ads;
import ru.skypro.homework.model.Comment;
import ru.skypro.homework.model.UserInfo;
import ru.skypro.homework.repository.AdsRepository;
import ru.skypro.homework.repository.CommentRepository;
import ru.skypro.homework.service.CommentService;
import ru.skypro.homework.service.mapper.CommentMapper;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Collections;
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
        List<CommentDTO> commentDTOList = Collections.singletonList
                (commentMapper.commentToCommentDto((Comment) commentList));
        for (int i = 0; i < commentList.size(); i++) {
            if (commentList.get(i).getAuthor().getImageModel() != null) {
                commentDTOList.get(i).setAuthorImage
                        ("/users/image/" + commentList.get(i).getAuthor().getImageModel().getId());
            }
        }

        return commentDTOList;
    }

    @Override
    public CommentDTO addComment(long adsId, String text) {
        UserInfo user = authService.getCurrentUser();
        if (user == null) {
            throw new UserNotRegisteredException("The user is not registered");
        } else {
            Ads ads = adsRepository.findById(adsId).orElse(null);
            Comment comment = new Comment();
            comment.setText(text);
            comment.setAds(ads);
            comment.setAuthor(user);
            commentRepository.save(comment);
            return commentMapper.commentToCommentDto(comment);
        }
    }

    @Override
    public void deleteComment(long adsId, long commentId) {
        UserInfo user = authService.getCurrentUser();
        Ads ads = adsRepository.findById(adsId).orElse(null);
        if (user.getId() == ads.getAuthor().getId() || user.getRole().equals(Role.ADMIN)) {
            commentRepository.deleteById(commentId);
        }
    }

    @Override
    public CommentDTO updateComment(long adsId, long commentId, String text) {
        UserInfo user = authService.getCurrentUser();
        Ads ads = adsRepository.findById(adsId).orElse(null);
        Comment comment = commentRepository.findById(commentId).orElse(null);
        if (user.getId() == ads.getAuthor().getId() || user.getRole().equals(Role.ADMIN)) {
            comment.setText(text);
            commentRepository.save(comment);
        }
        return commentMapper.commentToCommentDto(comment);
    }
}
