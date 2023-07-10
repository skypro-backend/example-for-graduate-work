package ru.skypro.homework.service.impl;

import org.springframework.stereotype.Service;
import ru.skypro.homework.dto.Comment;
import ru.skypro.homework.dto.Comments;
import ru.skypro.homework.dto.CreateOrUpdateComment;

@Service
public class CommentsService {
    public Comments getCommentsById(Integer id) {
        return null;
    }

    public Comment addComment(Integer id, CreateOrUpdateComment text) {
        return null;
    }

    public void deleteComment(Integer adId, Integer commentId) {
    }

    public Comment updateComment(Integer adId, Integer commentId, CreateOrUpdateComment newComment) {
        return null;
    }
}
