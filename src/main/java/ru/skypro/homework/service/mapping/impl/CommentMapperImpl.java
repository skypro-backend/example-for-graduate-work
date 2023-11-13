package ru.skypro.homework.service.mapping.impl;

import org.springframework.stereotype.Component;
import ru.skypro.homework.dto.comment.Comments;
import ru.skypro.homework.dto.comment.CreateOrUpdateComment;
import ru.skypro.homework.entity.AdEntity;
import ru.skypro.homework.entity.Comment;
import ru.skypro.homework.service.mapping.CommentMapper;

import java.util.ArrayList;
import java.util.List;

@Component
public class CommentMapperImpl  implements CommentMapper {

    @Override
    public ru.skypro.homework.dto.comment.Comment commentEntityToCommentDto(Comment commentEntity) {
        if (commentEntity == null) {
            return null;
        }
        ru.skypro.homework.dto.comment.Comment comment = new ru.skypro.homework.dto.comment.Comment();
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
    public List<ru.skypro.homework.dto.comment.Comment> listFromCommentEntityToDto(List<Comment> inputCommentsList) {
        if (inputCommentsList == null) {
            return null;
        }
        List<ru.skypro.homework.dto.comment.Comment> mapList = new ArrayList<>(inputCommentsList.size());
        for (Comment comment : inputCommentsList) {
            mapList.add(commentEntityToCommentDto(comment));

        }
        return mapList;
    }

    @Override
    public Comments adCommentsToCommentsDTO(AdEntity ad) {
        if (ad == null) {
            return null;
        }
        Comments comments = new Comments();
        comments.setResults(listFromCommentEntityToDto(ad.getComments()));
        comments.setCount(ad.getComments().size());
        return comments;
    }

    @Override
    public Comment createOrUpdateCommentDtoToCommentEntity(CreateOrUpdateComment createOrUpdateComment) {
        if (createOrUpdateComment == null) {
            return null;
        }
        Comment comment = new Comment();
        comment.setText(createOrUpdateComment.getText());
        return comment;
    }
}
