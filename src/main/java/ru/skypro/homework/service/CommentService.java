package ru.skypro.homework.service;

import ru.skypro.homework.dto.comment.Comments;
import ru.skypro.homework.dto.comment.CreateOrUpdateComment;

import java.util.List;
import java.util.Optional;

public interface CommentService {

    Optional<Comments> createComment(String userLogin, Long id, CreateOrUpdateComment createComment);

    List<Comments> listCommentsAdById(Long id);

    Optional<Comments> editComment(String userLogin, Long adId, Long commentId, CreateOrUpdateComment updateComment);

    boolean deleteById(String userLogin, Long adId, Long commentId);

}
