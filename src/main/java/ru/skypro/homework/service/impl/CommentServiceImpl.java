package ru.skypro.homework.service.impl;

import lombok.RequiredArgsConstructor;
import org.slf4j.LoggerFactory;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import ru.skypro.homework.dto.CreateOrUpdateCommentDTO;
import ru.skypro.homework.exceptions.EmptyException;
import ru.skypro.homework.model.Comment;
import ru.skypro.homework.repository.CommentRepo;
import ru.skypro.homework.service.CommentService;


import java.util.List;
import java.util.logging.Logger;

@Service
@RequiredArgsConstructor
public class CommentServiceImpl implements CommentService {

    private final CommentRepo commentRepo;

    private final Logger logger = (Logger) LoggerFactory.getLogger(UserServiceImpl.class);

    @Override
    public List<Comment> getAllByCommentById(Long pk) {
        logger.info("Comment getAllByCommentById is running");
        return commentRepo.getAllByAdId(pk);
    }

    @Override
    public Comment createComment(CreateOrUpdateCommentDTO text) {
        logger.info("Comment createComment is running");
        return commentRepo.save(text);
    }

    @Override
    public Comment findById(Long commentId) {
        logger.info("Comment findById is running");
        return commentRepo.findById(Math.toIntExact(commentId)).orElseThrow(() -> new EmptyException("Comment not found"));
    }

    @Override
    public ResponseEntity<Comment> deleteComment(Long adId, Long commentId) {
        logger.info("Comment deleteComment is running");
        if (adId != null) {
            commentRepo.deleteById(Math.toIntExact(commentId));
        }
        throw new EmptyException("Cell is empty");
    }

    @Override
    public Comment updateComment(Long adId, Long commentId, CreateOrUpdateCommentDTO text) {
        logger.info("Comment updateComment is running");
        CreateOrUpdateCommentDTO existing = text;
        existing.getText();
        return commentRepo.save(text);
    }
}
