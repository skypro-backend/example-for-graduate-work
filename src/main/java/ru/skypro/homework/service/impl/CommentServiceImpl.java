package ru.skypro.homework.service.impl;

import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import ru.skypro.homework.dto.*;
import ru.skypro.homework.exception.CommentNotFoundException;
import ru.skypro.homework.exception.UserNotFoundException;
import ru.skypro.homework.mappers.AdMapper;
import ru.skypro.homework.mappers.CommentMapper;
import ru.skypro.homework.model.Comment;
import ru.skypro.homework.model.Ad;
import ru.skypro.homework.model.Image;
import ru.skypro.homework.model.User;
import ru.skypro.homework.repository.AdRepository;
import ru.skypro.homework.repository.CommentRepository;
import ru.skypro.homework.service.CommentService;

import javax.validation.constraints.NotNull;
import java.util.List;
import java.util.stream.Collectors;

@RequiredArgsConstructor
@Service

public class CommentServiceImpl implements CommentService {

    private final CommentRepository commentRepository;
    private final CommentMapper commentMapper;

    @Override
    public CommentsDto getComments(int adId) {
        List<CommentDto> comments = commentRepository.findByAdId(adId).stream()
                .map(CommentMapper::commentToCommentDto)
                .collect(Collectors.toList());
                return new CommentsDto (comments.size(), comments);
    }

    @Override
    public CommentDto addComment(int adId, CreateOrUpdateCommentDto commentDto) {

        Comment comment = commentMapper.createOrUpdateDtoFromComment(commentDto, comment = new Comment);

            comment.setAd(adId);

            Comment savedComment = commentRepository.save(comment);

           return commentMapper.commentToCommentDto(savedComment);
        }


    @Override
    public void deleteComment(int adId, int commentId) {

        commentRepository.deleteById(commentId);
    }

    @Override
    public CommentDto updateComment(int adId, int commentId, CreateOrUpdateCommentDto commentDto) {
       Comment comment = commentRepository.findById(commentId).orElseThrow(CommentNotFoundException::new);
       commentRepository.save(comment);
    }
}