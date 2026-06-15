package ru.skypro.homework.service;

import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;
import ru.skypro.homework.dto.CommentDTO;
import ru.skypro.homework.model.Comment;

@Service
public class CommentService {

    private final ModelMapper mapper;

    public CommentService(ModelMapper mapper) {
        this.mapper = mapper;
    }

    public CommentDTO toDTO(Comment comment) {
        return mapper.map(comment, CommentDTO.class);
    }

    public Comment toEntity(CommentDTO dto) {
        return mapper.map(dto, Comment.class);
    }
}
