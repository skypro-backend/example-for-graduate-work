package ru.skypro.homework.service;

import ru.skypro.homework.dto.comment.Comments;
import ru.skypro.homework.dto.comment.CreateOrUpdateComment;

import java.util.List;
import java.util.Optional;

public interface CommentService {

    Optional<Comments> addComment(int id, CreateOrUpdateComment createComment);

    List<Comments> getComments(Integer id);

    Optional<Comments> editComment( Integer adId, Integer commentId);

    boolean deleteById( Integer adId, Integer commentId, CreateOrUpdateComment createOrUpdateComment);

}
