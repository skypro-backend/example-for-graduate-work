package ru.skypro.homework.service;

import com.fasterxml.jackson.core.JsonProcessingException;
import ru.skypro.homework.dto.CommentDTO;
import ru.skypro.homework.dto.CommentsDTO;
import ru.skypro.homework.dto.CreateOrUpdateCommentDTO;

public interface CommentService {
    CommentsDTO receivingAdComments(int adId);

    void deleteComment(int adId, int commentId);

    CommentDTO addComment(int adId, CreateOrUpdateCommentDTO text);


    CommentDTO updateComment(int adId, int commentId, CreateOrUpdateCommentDTO text);
}
