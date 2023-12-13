package ru.skypro.homework.service;

import org.springframework.security.core.Authentication;
import ru.skypro.homework.dto.CommentDTO;
import ru.skypro.homework.dto.Comments;
import ru.skypro.homework.dto.CreateOrUpdateComment;

public interface CommentService {

    Comments findAdComments(int adId);
    CommentDTO createComment(int adId,
                             CreateOrUpdateComment createOrUpdateComment,
                             Authentication authentication);
    void deleteComment(int adId,
                       int commentId);
    CommentDTO editComment(int adId,
                           int commentId,
                           CreateOrUpdateComment createOrUpdateComment);

}
