package ru.skypro.homework.service;

import org.springframework.stereotype.Service;
import ru.skypro.homework.dto.CommentDTO;
import ru.skypro.homework.dto.ResponseWrapperComment;
@Service
public interface CommentService {


    ResponseWrapperComment getUserComments(int userId);

    CommentDTO addComment(int adId, CommentDTO commentDTO);

    CommentDTO updateComment(int adId, int commentId, CommentDTO commentDTO);

    void deleteComment(int adId, int commentId);
}
