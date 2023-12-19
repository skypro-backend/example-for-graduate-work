package ru.skypro.homework.mappers;

import lombok.Data;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Component;
import ru.skypro.homework.dto.CommentDTO;
import ru.skypro.homework.dto.CommentsDTO;
import ru.skypro.homework.model.Comment;

@Data
@Component
public class CommentMapper {

    private final ModelMapper modelMapper;

    public Comment convertToComment(CommentDTO commentDTO) {
        return modelMapper.map(commentDTO, Comment.class);
    }

    public CommentsDTO convertToCommentsDTO(Comment comment) {
    return modelMapper.map(comment, CommentsDTO.class);
}

    public CommentDTO convertToCommentDTO(Comment comment) {
        return modelMapper.map(comment, CommentDTO.class);
    }
}
