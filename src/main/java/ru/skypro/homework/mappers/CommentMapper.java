package ru.skypro.homework.mappers;

import ru.skypro.homework.dto.CommentDto;
import ru.skypro.homework.models.Comment;

public class CommentMapper {

    public static Comment toComment(CommentDto commentDto) {
        if (commentDto == null) {
            throw new NullPointerException("Tried to map null to Comment");
        }

        Comment comment = new Comment();

        comment.setAuthorId(commentDto.getAuthorId());
        comment.setAuthorImage(commentDto.getAuthorImage());
        comment.setAuthorFirstName(commentDto.getAuthorFirstName());
        comment.setCreatedAt(commentDto.getCreatedAt());
        comment.setPkId(commentDto.getPkId());
        comment.setText(commentDto.getText());

        return comment;
    }

    public static CommentDto fromComment(Comment comment) {
        if (comment == null) {
            throw new NullPointerException("Tried to map null to CommentDto");
        }

        CommentDto commentDto = new CommentDto();

        commentDto.setAuthorId(comment.getAuthorId());
        commentDto.setAuthorImage(comment.getAuthorImage());
        commentDto.setAuthorFirstName(comment.getAuthorFirstName());
        commentDto.setCreatedAt(comment.getCreatedAt());
        commentDto.setPkId(comment.getPkId());
        commentDto.setText(comment.getText());

        return commentDto;
    }
}
