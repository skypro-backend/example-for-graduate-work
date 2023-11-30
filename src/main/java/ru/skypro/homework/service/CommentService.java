package ru.skypro.homework.service;

import org.springframework.http.ResponseEntity;
import org.springframework.security.core.userdetails.UserDetails;
import ru.skypro.homework.dto.Comment;
import ru.skypro.homework.dto.Comments;
import ru.skypro.homework.dto.CreateOrUpdateComment;

public interface CommentService {

    Comments getCommentsByAdId(Integer id);
    Comment addCommentToAd(Integer id, CreateOrUpdateComment commentDetails, UserDetails userDetails);
    ResponseEntity<String> deleteComment(Long adId, Integer commentId, UserDetails userDetails);
}
