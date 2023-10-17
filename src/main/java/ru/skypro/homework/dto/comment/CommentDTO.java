package ru.skypro.homework.dto.comment;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import ru.skypro.homework.entity.Comment;

import java.time.LocalDateTime;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class CommentDTO {
    Integer pk;
    Integer author;
    String authorImage;
    String authorFirstName;
    //    int createdAt;
    LocalDateTime createdAt;
    String text;

    public static CommentDTO CommentDTOfromComment(Comment comment) {
        CommentDTO commentDTO = new CommentDTO();
        commentDTO.setPk(comment.getPk());
        commentDTO.setAuthor(comment.getUsers().getId());
        commentDTO.setAuthorImage(comment.getUsers().getImage());
        commentDTO.setAuthorFirstName(comment.getUsers().getFirstName());
        commentDTO.setCreatedAt(commentDTO.getCreatedAt());
        return commentDTO;
    }

    public Comment toComment() {
        Comment comment = new Comment();
        comment.setPk(this.getPk());

        comment.setCreatedAt(this.getCreatedAt());
        comment.setText(this.getText());

        return comment;
    }

}
