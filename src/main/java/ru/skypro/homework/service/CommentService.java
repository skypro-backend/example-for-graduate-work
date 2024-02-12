package ru.skypro.homework.service;

import ru.skypro.homework.dto.CommentDTO;
import ru.skypro.homework.dto.CreateOrUpdateComment;

import java.util.List;

public interface CommentService {
    List<CommentDTO> getComments(Long adId);
    CommentDTO addComment(Long adId, CreateOrUpdateComment comment);
    void deleteComment(Long adId, Long commentId);
    CommentDTO updateComment(Long adId, Long commentId, CreateOrUpdateComment comment);
}
