package ru.skypro.homework.transformer;

import org.springframework.stereotype.Component;
import ru.skypro.homework.dto.Comment;
import ru.skypro.homework.dto.CreateOrUpdateComment;
import ru.skypro.homework.entity.CommentEntity;
import ru.skypro.homework.entity.UserEntity;

@Component
public class CommentTransformer {

    public Comment commentEntityToComment(CommentEntity commentEntity) {
        if (commentEntity == null) {
            return null;
        }
        Comment comment = new Comment();
        UserEntity user = commentEntity.getUser();
        comment.setAuthor(user.getId());
        comment.setAuthorImage(user.getImage());
        comment.setAuthorFirstName(user.getFirstName());
        comment.setCreatedAt(commentEntity.getCreatedAt());
        comment.setPk(commentEntity.getId());
        comment.setText(commentEntity.getText());
        return comment;
    }

    public CommentEntity commentToCommentEntity(Comment comment) {
        if (comment == null) {
            return null;
        }
        CommentEntity commentEntity = new CommentEntity();
        UserEntity user = new UserEntity();
        user.setId(comment.getAuthor());
        user.setImage(comment.getAuthorImage());
        user.setFirstName(comment.getAuthorFirstName());
        commentEntity.setUser(user);
        commentEntity.setCreatedAt(comment.getCreatedAt());
        commentEntity.setId(comment.getPk());
        commentEntity.setText(comment.getText());
        return commentEntity;
    }

    public CommentEntity createOrUpdateCommentToCommentEntity (CreateOrUpdateComment createOrUpdateComment) {
        if (createOrUpdateComment == null) {
            return null;
        }
        CommentEntity commentEntity = new CommentEntity();
        commentEntity.setText(createOrUpdateComment.getText());
        return commentEntity;
    }
}
