package ru.skypro.homework.service;

import ru.skypro.homework.dto.comment.CommentDTO;
import ru.skypro.homework.dto.comment.CreateOrUpdateComment;

import java.util.List;

public interface CommentService {

    List<CommentDTO> getComments(Integer adId);

    void addComment(Integer id, CreateOrUpdateComment createOrUpdateComment);

    void deleteComment(Integer adId, Integer commentId);

    void updateComment(Integer adId, Integer commentId);
}
