package ru.skypro.homework.service.impl;

import lombok.RequiredArgsConstructor;
import org.slf4j.LoggerFactory;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import ru.skypro.homework.dto.CommentDTO;
import ru.skypro.homework.dto.CommentsDTO;
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

    private final Logger logger = (Logger) LoggerFactory.getLogger(CommentServiceImpl.class);

    @Override
    public CommentsDTO getAllByCommentById(Long pk) {
        logger.info("Comment getAllByCommentById is running");
        commentRepo.count();
        return commentRepo.getAllByAdId(pk);
    }

    @Override
    public Comment createComment(CreateOrUpdateCommentDTO text) {
        logger.info("Comment createComment is running");
        final var comment = commentRepo.getCommentByText(String.valueOf(text)).orElseThrow();
                comment.setText(String.valueOf(text));
        return commentRepo.save(comment);
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
        final var comment = commentRepo.findById(Math.toIntExact(commentId)).orElseThrow();
        comment.setText(String.valueOf(text));
        return commentRepo.save(comment);
    }
}
