package ru.skypro.homework.mapper;

import lombok.NonNull;
import org.springframework.stereotype.Component;
import ru.skypro.homework.dto.CommentDto;
import ru.skypro.homework.entity.Comment;

@Component
public class CommentMapper {

    public CommentDto toDto(@NonNull Comment comment) {
        CommentDto commentDto = new CommentDto();

        commentDto.setPk(comment.getPk());
        commentDto.setText(comment.getText());
        commentDto.setAuthorImage(comment.getAuthor().getImage());
        commentDto.setAuthorFirstName(comment.getAuthor().getFirstName());
        commentDto.setCreatedAt(comment.getCreatedAt());

        return commentDto;
    }

    public Comment toEntity(CommentDto commentDto) {
        Comment comment = new Comment();

        comment.setText(commentDto.getText());

        return comment;
    }

}
