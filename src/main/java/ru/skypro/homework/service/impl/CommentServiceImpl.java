package ru.skypro.homework.service.impl;

import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Service;
import ru.skypro.homework.dto.*;
import ru.skypro.homework.exception.AdNotFoundException;
import ru.skypro.homework.exception.CommentNotFoundException;
import ru.skypro.homework.exception.UserNotFoundException;
import ru.skypro.homework.mappers.AdMapper;
import ru.skypro.homework.mappers.CommentMapper;
import ru.skypro.homework.mappers.UserMapper;
import ru.skypro.homework.model.Comment;
import ru.skypro.homework.model.Ad;
import ru.skypro.homework.model.Image;
import ru.skypro.homework.model.User;
import ru.skypro.homework.repository.AdRepository;
import ru.skypro.homework.repository.CommentRepository;
import ru.skypro.homework.repository.UserRepository;
import ru.skypro.homework.service.CommentService;

import javax.validation.constraints.NotNull;
import java.sql.Timestamp;
import java.time.LocalDateTime;
import java.time.temporal.ChronoUnit;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@RequiredArgsConstructor
@Service

public class CommentServiceImpl implements CommentService {

    private final CommentRepository commentRepository;
    private final CommentMapper commentMapper;
    private final AdRepository adRepository;
    private final UserRepository userRepository;

    @Override
    public CommentDto createComment(Integer id,
                                    CreateOrUpdateCommentDto createCommentDto,
                                    Authentication authentication) {
        Timestamp localDateTime = Timestamp.valueOf(LocalDateTime.now().truncatedTo(ChronoUnit.MINUTES));
        Comment comment = new Comment();
        Ad ad = adRepository.findAdById(id);
        if (ad == null) {
            return null;
        }
        comment.setText(createCommentDto.getText());
        comment.setCreatedAt(localDateTime);
        comment.setAd(ad);
        comment.setAuthor(userRepository.findByEmail(authentication.getName()).orElseThrow(UserNotFoundException::new));
        commentRepository.save(comment);
        return commentMapper.commentToCommentDto(comment);
    }
    @Override
    public CommentsDto getComments(Integer adId) {
        List<Comment> comment = commentRepository.findByAd_Id(adId);
        if (comment == null) {
            return null;
        }
        List<CommentDto> commentList = comment.stream()
                .map(commentMapper::commentToCommentDto)
                .collect(Collectors.toList());
        return new CommentsDto (commentList.size(), commentList);
    }

    @Override
    public void deleteComment( Integer adId, Integer commentId) {
        Comment comment = commentRepository.findById(commentId);
        commentRepository.delete(comment);
    }

 @Override
    public CommentDto updateComment (Integer adId, Integer commentId, CreateOrUpdateCommentDto commentDto) {
     Comment comment = commentRepository.findById(commentId);
     if (comment != null) {
         comment.setText(commentDto.getText());
         commentRepository.save(comment);
         return commentMapper.commentToCommentDto(comment);
     }
     return null;
 }
}