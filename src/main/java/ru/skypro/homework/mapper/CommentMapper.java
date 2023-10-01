package ru.skypro.homework.mapper;

import ru.skypro.homework.dto.CommentDTO;
import ru.skypro.homework.entity.Comment;


public class CommentMapper {
    public static CommentDTO fromComment(Comment comment) {
        return new CommentDTO()
                .setAuthor(comment.getUser().getId())
                .setPk(comment.getPk())
                .setText(comment.getText())
                .setCreatedAt(comment.getCreatedAt())
                .setAuthorImage(comment.getUser().getImage())
                .setAuthorFirstName(comment.getUser().getFirstName());
    }

}
