package ru.skypro.homework.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import ru.skypro.homework.model.Ad;
import ru.skypro.homework.model.Comment;

import java.time.LocalDate;
import java.time.LocalDateTime;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class CommentDTO {
    private int authorId;
//    private String authorImage;
    private String authorFirstName;
    private LocalDateTime createAt;
    private int pk;
    private String text;

    public static CommentDTO fromComment(Comment comment) {
        return new CommentDTO(comment.getUser().getId(),comment.getUser().getFirstName(), comment.getTime(), comment.getId(),comment.getText());
    }
}
