package ru.skypro.homework.service.mapper;

import org.springframework.stereotype.Component;
import ru.skypro.homework.dto.AdsDTO;
import ru.skypro.homework.dto.CommentDTO;
import ru.skypro.homework.model.Ads;
import ru.skypro.homework.model.Comment;
/**
 * Класс, содержащий методы для преобразования класса {@link Comment} в DTO
 */
@Component
public class CommentMapper {

    /**
     * Метод преобразует {@link Comment} в {@link CommentDTO}
     * @param comment
     * @return {@link CommentDTO}
     */
    public CommentDTO commentToCommentDto(Comment comment) {
        CommentDTO commentDTO = new CommentDTO();
        commentDTO.setAuthor(comment.getAuthor().getId());
        if (comment.getAuthor().getImage() != null) {
            commentDTO.setAuthorImage("/users/image/" + comment.getAuthor().getImage().getId());
        }
        commentDTO.setAuthorFirstName(comment.getAuthor().getFirstName());
        commentDTO.setCreatedAt(comment.getCreatedAt());
        commentDTO.setPk(comment.getId());
        commentDTO.setText(comment.getText());

        return commentDTO;
    }


}
