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
    public CommentsDto getComments(Integer adId) {
        List<CommentDto> comments = commentRepository.findByAd_Pk(adId).stream()
                .map(CommentMapper::commentToCommentDto)
                .collect(Collectors.toList());

        return CommentsDto.builder()
                .count(comments.size())
                .results(comments)
                .build();
    }

    @Override
    public CommentDto addComment(Integer adId, CreateOrUpdateCommentDto commentDto) {
        Comment comment = new Comment();
        Ad ad = adRepository.findById(adId).orElseThrow(() -> new AdNotFoundException());

        comment.setAd(ad);

        commentMapper.createOrUpdateDtoFromComment(comment);

        Comment savedComment = commentRepository.save(comment);

        return commentMapper.commentToCommentDto(savedComment);
    }



    @Override
    public void deleteComment( Integer commentId, Integer adId) {
        commentRepository.findByIdAndAd_Pk(commentId, adId)
                .ifPresentOrElse(comment -> commentRepository.deleteById(comment.getId()), () -> {
                    throw new CommentNotFoundException();
                });
    }

 @Override
    public CommentDto updateComment (Integer adId, Integer commentId, CreateOrUpdateCommentDto commentDto) {
     return commentRepository.findByIdAndAd_Pk(commentId, adId)
             .map(comment -> {
                 if (commentDto.getText() != null) {
                     comment.setText(commentDto.getText());
                     comment = commentRepository.save(comment);
                 }
                 return commentMapper.commentToCommentDto(comment);
             })
             .orElseThrow(CommentNotFoundException::new);
 }


}