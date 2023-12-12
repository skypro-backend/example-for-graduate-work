package ru.skypro.homework.service;

import org.springframework.security.core.userdetails.UserDetails;
import ru.skypro.homework.dto.Comment;
import ru.skypro.homework.dto.Comments;
import ru.skypro.homework.dto.CreateOrUpdateComment;

public interface CommentService {

    Comments getCommentsByAdId(int id);

    Comment addCommentToAd(int id, CreateOrUpdateComment commentDetails, UserDetails userDetails);

    void deleteComment(int adId, int commentId, UserDetails userDetails);

    Comment updateComment(int adId, int commentId, CreateOrUpdateComment commentDetails, UserDetails userDetails);
}
