package ru.skypro.homework.service.impl;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import ru.skypro.homework.dto.CommentDTO;
import ru.skypro.homework.dto.CreateCommentDTO;
import ru.skypro.homework.dto.GetCommentDTO;
import ru.skypro.homework.dto.Role;
import ru.skypro.homework.exception.AdsNotFoundException;
import ru.skypro.homework.exception.CommentNotFoundException;
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
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class CommentServiceImpl implements CommentService {

    private final CommentRepository commentRepository;
    private final AdsRepository adsRepository;
    private final CommentMapper commentMapper;
    private final AuthServiceImpl authService;
    @Override
    @Transactional
    public GetCommentDTO getCommentsByAdsId(Integer id) {
        Ads ads = adsRepository.findById(id).orElseThrow(AdsNotFoundException::new);
        List<CommentDTO> commentList = ads.getComments().stream()
                .map(commentMapper::commentToCommentDto)
                .collect(Collectors.toList());

        GetCommentDTO getCommentDTO = new GetCommentDTO();
        getCommentDTO.setResults(commentList);
        getCommentDTO.setCount(commentList.size());

        return getCommentDTO;
    }

    @Override
    @Transactional
    public CommentDTO addComment(Integer adsId, CreateCommentDTO createCommentDTO) {
        UserInfo user = authService.getCurrentUser();
        Ads ads = adsRepository.findById(adsId).orElseThrow(AdsNotFoundException::new);
        Comment comment = new Comment();

        comment.setText(createCommentDTO.getText());
        comment.setAds(ads);
        comment.setAuthor(user);
        comment.setCreatedAt(System.currentTimeMillis());
        commentRepository.save(comment);
        return commentMapper.commentToCommentDto(comment);

    }

    @Override
    @Transactional
    public void deleteComment(Integer adsId, Integer commentId) {
        UserInfo user = authService.getCurrentUser();
        Ads ads = adsRepository.findById(adsId).orElseThrow(AdsNotFoundException::new);
        if (user.getId().equals(ads.getAuthor().getId()) || user.getRole().equals(Role.ADMIN)) {
            commentRepository.deleteById(commentId);
        }
    }

    @Override
    @Transactional
    public CommentDTO updateComment(Integer adsId, Integer commentId, CreateCommentDTO createCommentDTO) {
        UserInfo user = authService.getCurrentUser();
        Comment comment = commentRepository.findById(commentId).orElseThrow(CommentNotFoundException::new);
        if (user.getId().equals(comment.getAuthor().getId()) || user.getRole().equals(Role.ADMIN)) {
            comment.setText(createCommentDTO.getText());
            commentRepository.save(comment);
        }
        return commentMapper.commentToCommentDto(comment);
    }
}
