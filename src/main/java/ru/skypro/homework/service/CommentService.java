package ru.skypro.homework.service;


import org.springframework.security.core.Authentication;
import ru.skypro.homework.dto.*;

public interface CommentService {
    CommentsDto getComments(Integer adId);
/*    CommentDto addComment(CommentDto commentDto);
    CommentDto addComment(CreateOrUpdateCommentDto commentDto);*/
    void deleteComment(Integer adId, Integer commentId) ;
    CommentDto updateComment(Integer adId, Integer commentId, CreateOrUpdateCommentDto commentDto);

    CommentDto createComment(Integer id,
                             CreateOrUpdateCommentDto createCommentDto,
                             Authentication authentication);
         }