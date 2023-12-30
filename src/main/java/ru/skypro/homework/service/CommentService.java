package ru.skypro.homework.service;
import ru.skypro.homework.dto.Comment;
import ru.skypro.homework.dto.Comments;
import ru.skypro.homework.dto.CreateOrUpdateComment;
import ru.skypro.homework.dto.CustomUserDetails;

public interface CommentService {
    Comments getComments(Integer id);
    Comment addComment(Integer id, CreateOrUpdateComment createOrUpdateComment, CustomUserDetails userDetails);
    void deleteComment(Integer adId, Integer commentId, CustomUserDetails userDetails);
    Comment updateComment (Integer adId,
                          Integer commentId,
                          CreateOrUpdateComment createOrUpdateComment,
                           CustomUserDetails userDetails);

}
