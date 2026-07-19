package ru.skypro.homework.mapper;

import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;
import ru.skypro.homework.dto.CommentDTO;
import ru.skypro.homework.dto.CreateOrUpdateAdDTO;
import ru.skypro.homework.dto.CreateOrUpdateCommentDTO;
import ru.skypro.homework.model.Ad;
import ru.skypro.homework.model.Comment;

@Service
public class CommentMapper {

    private final ModelMapper mapper;

    public CommentMapper(ModelMapper mapper) {
        this.mapper = mapper;
    }

    public CommentDTO toDTO(Comment comment) {
        return mapper.map(comment, CommentDTO.class);
    }

    public Comment toEntity(CreateOrUpdateCommentDTO dto) {
        return mapper.map(dto, Comment.class);
    }

    public void updateEntityFromDTO(CreateOrUpdateCommentDTO dto, Comment existingComment) {
        mapper.map(dto, existingComment);
    }
}
