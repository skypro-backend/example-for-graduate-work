package ru.skypro.homework.service;

import ru.skypro.homework.dto.CommentDto;
import ru.skypro.homework.dto.Comments;
import ru.skypro.homework.dto.CreateOrUpdateComment;

public interface CommentService {
    Comments getAllCommentsForAdById(Integer adPk);
    Comments createNewComment(Integer adPk, CreateOrUpdateComment createOrUpdateCommentDto);
    void deleteComment (Integer adPk, Integer commentPk);
    CommentDto updateComment (Integer adPk, Integer commentId, CreateOrUpdateComment createOrUpdateCommentDto);
}
