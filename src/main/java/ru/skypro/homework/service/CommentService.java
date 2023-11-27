package ru.skypro.homework.service;

import ru.skypro.homework.dto.Comment;
import ru.skypro.homework.dto.Comments;
import ru.skypro.homework.dto.CreateOrUpdateComment;

public interface CommentService {

    Comments getCommentsByAdId(Integer adId);
    Comment addCommentToAd(Integer adId, CreateOrUpdateComment dto);
    Comment updateComment(Integer id, Integer adId, CreateOrUpdateComment dto);
    void deleteComment(Integer id, Integer adId);
    boolean existsCommentByIdAndUsername(Integer id, String username);
}
