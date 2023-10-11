package ru.skypro.homework.service.impl;

import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Service;
import ru.skypro.homework.dto.comment.Comment;
import ru.skypro.homework.dto.comment.Comments;
import ru.skypro.homework.dto.comment.CreateOrUpdateComment;
import ru.skypro.homework.service.CommentService;


@Service
public class CommentServiceImpl implements CommentService {
    @Override
    public Comments getComments(Authentication authentication, int id) {
        return null;
    }

    @Override
    public Comment addComment(Authentication authentication, int id, CreateOrUpdateComment comment) {
        return null;
    }

    @Override
    public void deleteComment(Authentication authentication, int adId, int commentId) {

    }

    @Override
    public Comment updateComment(Authentication authentication, int adId, int commentId, CreateOrUpdateComment createOrUpdateComment) {
        return null;
    }
}
