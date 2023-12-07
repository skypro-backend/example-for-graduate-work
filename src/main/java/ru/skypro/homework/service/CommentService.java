package ru.skypro.homework.service;

import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import ru.skypro.homework.dto.Comment;
import ru.skypro.homework.dto.Comments;
import ru.skypro.homework.dto.CreateOrUpdateComment;

public interface CommentService {

    Comments getCommentsByAdId(Integer id);
    Comment addCommentToAd(Integer id, CreateOrUpdateComment commentDetails, UserDetails userDetails);
    boolean deleteComment(Integer adId, Integer commentId, UserDetails userDetails);

    public Comment updateComment(Integer adId, Integer commentId, CreateOrUpdateComment commentDetails, UserDetails userDetails);
}
