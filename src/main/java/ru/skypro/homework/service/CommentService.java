package ru.skypro.homework.service;

import com.fasterxml.jackson.core.JsonProcessingException;
import ru.skypro.homework.dto.CommentDTO;
import ru.skypro.homework.dto.CommentsDTO;

public interface CommentService {
    CommentsDTO receivingAdComments(int adId);

    void deleteComment(int adId, int commentId);

    CommentDTO addComment(int adId, String text);


    CommentDTO updateComment(int adId, int commentId, String text);
}
