package ru.skypro.homework.service;

import ru.skypro.homework.dto.Comment;
import ru.skypro.homework.dto.CreateOrUpdateComment;

import java.util.List;

public interface CommentService {
    List<Comment> getCommentsForAd(Long id);

    Comment addCommentToAd(Long id, CreateOrUpdateComment commentDetails);

    boolean deleteComment(Long adId, Long commentId);

    Comment updateComment(Long adId, Long commentId, CreateOrUpdateComment commentDetails);
}
