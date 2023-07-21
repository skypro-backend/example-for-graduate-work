package ru.skypro.homework.service.impl;

import org.springframework.stereotype.Service;
import ru.skypro.homework.dto.*;
import ru.skypro.homework.model.Ad;
import ru.skypro.homework.model.Comment;
import ru.skypro.homework.model.User;
import ru.skypro.homework.repository.AdsRepository;
import ru.skypro.homework.repository.CommentsRepository;
import ru.skypro.homework.service.CommentMapperService;

import java.time.Instant;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class CommentsService {

    private final CommentsRepository commentsRepository;
    private final AdsRepository adsRepository;
    private final UserService userService;
    private final CommentMapperService commentMapperService;


    public CommentsService(CommentsRepository commentsRepository, AdsRepository adsRepository, UserService userService, CommentMapperService commentMapperService) {
        this.commentsRepository = commentsRepository;
        this.adsRepository = adsRepository;
        this.userService = userService;
        this.commentMapperService = commentMapperService;
    }


    public CommentsDto getCommentsById(Integer IdAd) {
        Ad ad = adsRepository.findById(IdAd).orElseThrow();
        List<Comment> commentList = commentsRepository.findByAd(ad);
        List<CommentDto> commentsDtoList = commentList.stream()
                .map(e -> commentMapperService.mapToDto(e))
                .collect(Collectors.toList());

        return new CommentsDto(commentsDtoList.size(), commentsDtoList);

    }

    public CommentDto addComment(Integer IdAd, CreateOrUpdateCommentDto text) {
        User user = userService.getUser();
        Ad ad = adsRepository.findById(IdAd).orElseThrow();
        Comment comment = new Comment(ad,
                user,
                Instant.now().toEpochMilli(),
                text.getText());
        commentsRepository.save(comment);
        return commentMapperService.mapToDto(comment);

    }

    public void deleteComment(Integer IdAd, Integer commentId) {
        User user = userService.getUser();
        Comment comment = commentsRepository.findByAdPkAndCommentId(IdAd, commentId);
        if(user.getUserId().equals(comment.getAuthor().getUserId()) || user.getRoleDto() == RoleDto.ADMIN){
            commentsRepository.delete(comment);
        } else throw new RuntimeException("Такой комментарий не существует");
    }


    public CommentDto updateComment(Integer IdAd, Integer commentId, CreateOrUpdateCommentDto newComment) {
        User user = userService.getUser();
        Comment comment = commentsRepository.findByAdPkAndCommentId(IdAd, commentId);
        if(user.getUserId().equals(comment.getAuthor().getUserId()) || user.getRoleDto() == RoleDto.ADMIN){
            comment.setText(newComment.getText());
            commentsRepository.save(comment);
            return commentMapperService.mapToDto(comment);
        } else throw new RuntimeException("Такой комментарий не существует");
    }
}
