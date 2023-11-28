package ru.skypro.homework.service.mapper;

import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.springframework.stereotype.Component;
import ru.skypro.homework.dto.CommentDTO;
import ru.skypro.homework.model.Comment;

@Component
public class CommentMapper {


    public CommentDTO commentToCommentDto(Comment comment) {
        CommentDTO commentDTO = new CommentDTO();
        commentDTO.setAuthor(comment.getAuthor().getId());
        if (comment.getAuthor().getImage() != null) {
            commentDTO.setAuthorImage("/users/image/" + comment.getAuthor().getImage().getId());
        }
        commentDTO.setAuthorFirstName(comment.getAuthor().getFirstName());
        commentDTO.setCreatedAt(comment.getCreatedAt());
        commentDTO.setPk(comment.getId());
        commentDTO.setText(comment.getText());

        return commentDTO;
    }


}
