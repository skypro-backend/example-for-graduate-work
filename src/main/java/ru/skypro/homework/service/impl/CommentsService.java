package ru.skypro.homework.service.impl;

import lombok.RequiredArgsConstructor;
import org.springframework.security.access.annotation.Secured;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Service;
import ru.skypro.homework.dto.*;
import ru.skypro.homework.model.Ad;
import ru.skypro.homework.model.Comment;
import ru.skypro.homework.model.User;
import ru.skypro.homework.repository.AdsRepository;
import ru.skypro.homework.repository.CommentsRepository;
import ru.skypro.homework.service.AdMapperService;
import ru.skypro.homework.service.CommentMapperService;

import java.time.Instant;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class CommentsService {

    private final CommentsRepository commentsRepository;
    private final AdsRepository adsRepository;
    private final UserService userService;
    private final CommentMapperService commentMapperService;

    public CommentsDto getCommentsById(Integer adId) {
        List<Comment> comments = commentsRepository
                .findAll()
                .stream()
                .filter(comment -> comment.getAd() == adId)
                .collect(Collectors.toList());
        CommentsDto commentsDto = new CommentsDto();
        commentsDto.setCount(comments.size());
        commentsDto.setResults(commentMapperService.CommentListToCommentDtoToList(comments));
        return commentsDto;
    }

    public CommentDto addComment(Integer id, CreateOrUpdateCommentDto text) {
        User user = userService.getUser();
        Ad ad = adsRepository.findById(id).orElseThrow();
//        Integer author;
//        String authorImage;
//        String authorFirstName;
//        Long createdAt;
//        Integer pk;
//        String text;
        Comment comment = new Comment();
        comment.setAuthor(user);
        comment.setCreatedTime(Instant.now().toEpochMilli());
        comment.setText(text.getText());
        comment.setAd(ad.getPk());
        commentsRepository.save(comment);
        return commentMapperService.mapToDto(comment);

    }
    @Secured({"ADMIN", "USER"})
    public void deleteComment(Integer AdId, Integer commentId) {
        User user = userService.getUser();
        Comment comment = commentsRepository.getByCommentId(commentId);
         if (user.getId().equals(comment.getAuthor().getId()) || user.getRole() == Role.ADMIN) {
            commentsRepository.delete(comment);
        } else throw new RuntimeException("Такой комментарий не существует");
    }


    public CommentDto updateComment(Integer IdAd, Integer commentId, CreateOrUpdateCommentDto newComment) {
        User user = userService.getUser();
        Comment comment = commentsRepository.getByCommentId(commentId);
        if (user.getId().equals(comment.getAuthor().getId()) || user.getRole() == Role.ADMIN) {
            comment.setText(newComment.getText());
            commentsRepository.save(comment);
            return commentMapperService.mapToDto(comment);
        } else throw new RuntimeException("Такой комментарий не существует");
    }

    }


