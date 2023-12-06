package ru.skypro.homework.mapper;


import org.springframework.stereotype.Service;
import ru.skypro.homework.dto.CommentDTO;
import ru.skypro.homework.dto.CreateOrUpdateComment;
import ru.skypro.homework.model.Comment;
import ru.skypro.homework.model.Image;

@Service
public class CommentMapper {

    /**
     * Маппинг списка комментариев в список объектов DTO.
     */
    public CommentDTO mapToCommentDto(Comment comment) {
        CommentDTO commentDTO = new CommentDTO();
        commentDTO.setAuthor(comment.getUser().getId());
        commentDTO.setAuthorFirstName(comment.getUser().getFirstName());
        Image image = comment.getUser().getImage();
        if (image != null) {
            commentDTO.setAuthorImage("/image/" + image.getId());
        } else {
            commentDTO.setAuthorImage(null);
        }
        commentDTO.setCreatedAt(comment.getCreatedAt());
        commentDTO.setPk(comment.getPk());
        commentDTO.setText(comment.getText());
        return commentDTO;
    }
}
