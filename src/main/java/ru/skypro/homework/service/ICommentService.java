package ru.skypro.homework.service;

import ru.skypro.homework.comment.Comment;
import ru.skypro.homework.comment.Comments;
import ru.skypro.homework.comment.CreateOrUpdateComment;
import org.springframework.security.core.Authentication;

public interface ICommentService {
    Comments getCommentsForAd(long adId);
    Comments getMyAds(Authentication auth);
    Comment createComment(long adId, CreateOrUpdateComment data, Authentication auth);
    Comment updateComment(long adId, long commentId, CreateOrUpdateComment data, Authentication auth);
    void deleteComment(long adId, long commentId, Authentication auth);

}