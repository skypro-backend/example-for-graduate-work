package ru.skypro.homework.service.comment;


import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import ru.skypro.homework.dto.CommentDTO;
import ru.skypro.homework.projection.CommentView;
import ru.skypro.homework.projection.Comments;
import ru.skypro.homework.projection.CreateOrUpdateComment;

import java.util.List;

public interface CommentService {

    Comments getAllCommentsByAdId(Integer id);
    CommentView createComment(Integer id, CreateOrUpdateComment comment, Authentication authentication);
    void deleteComment(Integer commentId, Integer adId);
    CommentView updateComment(Integer adId, Integer commentId, CreateOrUpdateComment comment);
    CommentDTO findById(Integer id);
}
