package ru.skypro.homework.service.mapping.impl;

import org.springframework.stereotype.Component;
import ru.skypro.homework.dto.comment.Comment;
import ru.skypro.homework.dto.comment.Comments;
import ru.skypro.homework.dto.comment.CreateOrUpdateComment;
import ru.skypro.homework.entity.Ad;
import ru.skypro.homework.entity.CommentEntity;
import ru.skypro.homework.service.mapping.CommentMapper;

import java.util.ArrayList;
import java.util.List;

@Component
public class CommentMapperImpl  implements CommentMapper {

    @Override
    public Comment commentEntityToCommentDto(CommentEntity commentEntity) {
        if (commentEntity == null) {
            return null;
        }
        Comment comment = new Comment();
        comment.setAuthor(commentEntity.getUserRelated().getId());
        if (commentEntity.getUserRelated().getImageAvatar() != null) {
            comment.setImage("/users/" + commentEntity.getUserRelated().getImageAvatar().getId() + "/avatar");
        } else {
            comment.setImage(null);
        }
        comment.setAuthorFirstName(commentEntity.getUserRelated().getFirstName());
        comment.setCreatedAt(commentEntity.getCreatedAt());
        comment.setPk(commentEntity.getId());
        comment.setText(comment.getText());
        return comment;
    }



    @Override
    public List<Comment> listFromCommentEntityToDto(List<CommentEntity> inputCommentsList) {
        if (inputCommentsList == null) {
            return null;
        }
        List<Comment> mapList = new ArrayList<>(inputCommentsList.size());
        for (CommentEntity commentEntity : inputCommentsList) {
            mapList.add(commentEntityToCommentDto(commentEntity));

        }
        return mapList;
    }

    @Override
    public Comments adCommentsToCommentsDTO(Ad ad) {
        if (ad == null) {
            return null;
        }
        Comments comments = new Comments();
        comments.setResults(listFromCommentEntityToDto(ad.getComments()));
        comments.setCount(ad.getComments().size());
        return comments;
    }

    @Override
    public CommentEntity createOrUpdateCommentDtoToCommentEntity(CreateOrUpdateComment createOrUpdateComment) {
        if (createOrUpdateComment == null) {
            return null;
        }
        CommentEntity commentEntity = new CommentEntity();
        commentEntity.setText(createOrUpdateComment.getText());
        return commentEntity;
    }
}
