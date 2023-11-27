package ru.skypro.homework.mapper;

import ru.skypro.homework.dto.Comment;
import ru.skypro.homework.entity.CommentEntity;

public class CommentMapper {
    public static CommentEntity toCommentEntity(Comment comment) {
        if(comment == null){
            throw new NullPointerException("Is not available");
        }
        CommentEntity commentEntity = new CommentEntity();

        commentEntity.setAdId(comment.getAdId());
        commentEntity.setAuthor(comment.getAuthor());
        commentEntity.setAuthorFirstName(comment.getAuthorFirstName());
        commentEntity.setAuthorImage(comment.getAuthorImage());
        commentEntity.setPk(comment.getPk());
        commentEntity.setText(comment.getText());
        commentEntity.setCreatedAt(comment.getCreatedAt());


        return commentEntity;

    }

    public static Comment toComment(CommentEntity commentEntity){
        if(commentEntity == null){
            throw new NullPointerException("Is not available");
        }
        Comment comment = new Comment();

        comment.setAdId(commentEntity.getAdId());
        comment.setAuthor(commentEntity.getAuthor());
        comment.setAuthorFirstName(commentEntity.getAuthorFirstName());
        comment.setAuthorImage(commentEntity.getAuthorImage());
        comment.setPk(commentEntity.getPk());
        comment.setText(commentEntity.getText());
        comment.setCreatedAt(commentEntity.getCreatedAt());


        return comment;
    }
}