package ru.skypro.homework.service;

import org.springframework.stereotype.Service;
import ru.skypro.homework.dto.CommentDTO;
import ru.skypro.homework.mapper.CommentMapper;
import ru.skypro.homework.repository.CommentRepository;

@Service
public class CommentService {
    private final CommentRepository commentRepository;
    private final CommentMapper commentMapping;
    public CommentService(CommentRepository commentRepository, CommentMapper commentMapping) {
        this.commentRepository = commentRepository;
        this.commentMapping = commentMapping;
    }

    public void createComment(CommentDTO commentDTO) {
        commentRepository.save(commentMapping.mapToComment(commentDTO));
    }
}
