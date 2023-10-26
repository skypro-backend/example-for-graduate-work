package ru.skypro.homework.mapper;

import ru.skypro.homework.dto.CommentDTO;
import ru.skypro.homework.model.CommentModel;
import ru.skypro.homework.projections.Comments;

public class CommentMapper {
    public static CommentDTO toCommentDTO(CommentModel commentModel) {
        CommentDTO commentDTO = new CommentDTO();
        commentDTO.setPk(commentModel.getPk());
//        commentDTO.setAuthor(commentModel.getAuthor());
        commentDTO.setText(commentModel.getText());
        commentDTO.setCreatedAt(commentModel.getCreateAt());
        commentDTO.setAuthorFirstName(commentDTO.getAuthorFirstName());
        commentDTO.setAuthorImage(commentDTO.getAuthorImage());
        return commentDTO;
    }

    public static CommentModel toCommentModel(CommentDTO commentDTO) {
        CommentModel commentModel = new CommentModel();
        commentModel.setPk(commentModel.getPk());
        commentModel.setCreateAt(commentDTO.getCreatedAt());
        commentModel.setText(commentDTO.getText());
//        commentModel.setAuthor(commentDTO.getAuthor());
        return commentModel;
    }

    public static Comments toComments(CommentModel commentModel) {
        Comments comments = new Comments();
        comments.setCount(comments.getCount());
        comments.setResults(comments.getResults());
        return comments;
    }
}
