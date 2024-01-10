package ru.skypro.kakavito.mappers;

import lombok.Data;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;
import ru.skypro.kakavito.dto.CommentDTO;
import ru.skypro.kakavito.dto.CommentsDTO;
import ru.skypro.kakavito.model.Comment;
import ru.skypro.kakavito.model.User;

import java.time.ZoneId;
import java.util.List;
import java.util.stream.Collectors;

/**
 * Маппер для преобразования сущности Comment
 *
 * @see Comment
 */
@Data
@Component
public class CommentMapper {

    private final ModelMapper modelMapper;

    @Value("${query.to.get.image}")
    private String imageQuery;

    /**
     * Конвертирует в CommentDTO
     *
     * @param comment
     * @return CommenDTO
     * @see CommentDTO
     */
    public CommentDTO toDto(Comment comment) {
        CommentDTO commentDTO = modelMapper.map(comment, CommentDTO.class);
        commentDTO.setPk(comment.getId());
        commentDTO.setCreatedAt(comment.getCreatedAt().atZone(ZoneId.systemDefault()).toInstant().toEpochMilli());
        commentDTO.setText(comment.getText());
        User user = comment.getUser();
        if (user != null) {
            commentDTO.setAuthor(Math.toIntExact(user.getId()));
            commentDTO.setAuthorFirstName(user.getFirstName());
            commentDTO.setAuthorImage(imageQuery + user.getImage().getId());
        }
        return commentDTO;
    }

    /**
     * Конвертирует в Comments DTO
     *
     * @param comments
     * @return CommentsDTO
     * @see CommentsDTO
     */
    public CommentsDTO toCommentsDTO(List<Comment> comments) {
        CommentsDTO commentsDTO = new CommentsDTO();
        commentsDTO.setCount(comments.size());
        commentsDTO.setResults(comments.stream().map(this::toDto).collect(Collectors.toList()));
        return commentsDTO;
    }
}
