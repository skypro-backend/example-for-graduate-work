package ru.skypro.homework.service.impl;

import org.springframework.stereotype.Service;
import ru.skypro.homework.dto.Comment;
import ru.skypro.homework.dto.CreateOrUpdateComment;
import ru.skypro.homework.service.CommentService;

import java.util.List;

@Service
public class CommentServiceImpl implements CommentService {
    @Override
    public List<Comment> getCommentsForAd(Long id) {
        return null;
    }

    @Override
    public Comment addCommentToAd(Long id, CreateOrUpdateComment commentDetails) {
        return null;
    }

    @Override
    public boolean deleteComment(Long adId, Long commentId) {
        return false;
    }

    @Override
    public Comment updateComment(Long adId, Long commentId, CreateOrUpdateComment commentDetails) {
        return null;
    }
}
