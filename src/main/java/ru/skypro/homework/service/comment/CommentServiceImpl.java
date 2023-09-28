package ru.skypro.homework.service.comment;

import lombok.AllArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import ru.skypro.homework.projection.CreateOrUpdateComment;
import ru.skypro.homework.repository.CommentRepository;

@AllArgsConstructor
@Service
public class CommentServiceImpl implements CommentService{
    private final CommentRepository commentRepository;

    @Override
    public ResponseEntity<?> getAllCommentsByAdId(Integer id) {
        return null;
    }

    @Override
    public ResponseEntity<?> createComment(Integer id, CreateOrUpdateComment comment) {
        return null;
    }

    @Override
    public ResponseEntity<?> deleteComment(Integer adId, Integer commentId) {
        return null;
    }

    @Override
    public ResponseEntity<?> updateComment(Integer adId, Integer commentId, CreateOrUpdateComment comment) {
        return null;
    }
}