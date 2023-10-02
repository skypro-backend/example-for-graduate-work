package ru.skypro.homework.service.impl;

import ru.skypro.homework.dto.comment.Comments;
import ru.skypro.homework.dto.comment.CreateOrUpdateComment;
import ru.skypro.homework.service.CommentService;

import java.util.List;
import java.util.Optional;

public class CommentServiceImpl  implements CommentService {
    @Override
    public Optional<Comments> addComment(int id, CreateOrUpdateComment createComment) {
        return Optional.empty();
    }

    @Override
    public List<Comments> getComments(Integer id) {
        return null;
    }

    @Override
    public Optional<Comments> editComment(Integer adId, Integer commentId) {
        return Optional.empty();
    }

    @Override
    public boolean deleteById(Integer adId, Integer commentId, CreateOrUpdateComment createOrUpdateComment) {
        return false;
    }
}
