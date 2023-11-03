package ru.skypro.homework.mapper;

import ru.skypro.homework.dto.CommentDTO;
import ru.skypro.homework.model.CommentModel;
import ru.skypro.homework.projections.Comments;
import ru.skypro.homework.projections.CreateOrUpdateComment;

public class CommentMapper {
    public static CommentDTO toCommentDTO(CommentModel commentModel) {
        CommentDTO commentDTO = new CommentDTO();
        commentDTO.setPk(commentModel.getPk());
        commentDTO.setAuthor(commentModel.getUserModel().getId());
        commentDTO.setAuthorImage(commentModel.getUserModel().getImage().getId());
        commentDTO.setAuthorFirstName(commentModel.getUserModel().getFirstName());
        commentDTO.setCreatedAt(commentModel.getCreateAt());
        commentDTO.setText(commentModel.getText());
        return commentDTO;
    }

    public static CommentModel toCommentModel(CommentDTO commentDTO) {
        CommentModel commentModel = new CommentModel();
        commentModel.setPk(commentModel.getPk());
        commentModel.setCreateAt(commentDTO.getCreatedAt());
        commentModel.setText(commentDTO.getText());
        return commentModel;

    }

    public static Comments toComments(CommentModel commentModel) {
        Comments comments = new Comments();
        comments.setCount(comments.getCount());
        comments.setResults(comments.getResults());
        return comments;
    }
    public static CreateOrUpdateComment toCreateOrUpdateComment(Comments comments) {
        CreateOrUpdateComment createOrUpdateComment= new CreateOrUpdateComment();
       createOrUpdateComment.setText(createOrUpdateComment.getText());
        return createOrUpdateComment;
    }
    public static Comments toCommentsAdd(CreateOrUpdateComment createOrUpdateComment) {
        Comments comments = new Comments();
        comments.setResults(comments.getResults());
        comments.setResults(comments.getResults());
        return comments;
    }
}
