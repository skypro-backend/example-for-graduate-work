package ru.skypro.homework.dto;

import lombok.Data;
import ru.skypro.homework.model.Comment;

import java.time.Instant;

@Data
public class CommentDTO {
    // id автора комментария
    private Long author;
    // Ссылка на аватар автора комментария
    private String authorImage;
    // Имя автора комментария
    private String authorFirstName;
    // Дата и время создания комментария
    private Instant createdAt;
    // id комментария
    private Long pk;
    // Текст комментария
    private String text;

    public Comment toComment() {
        Comment comment = new Comment();
        comment.setId(pk);
        comment.setText(text);
        comment.setCreationDateTime(createdAt);
        return comment;
    }

    public static CommentDTO fromComment(Comment comment) {
        CommentDTO dto = new CommentDTO();
        dto.setAuthor(comment.getAuthor().getId());
        // todo
        dto.setAuthorImage(comment.getAuthor().getImage());
        dto.setAuthorFirstName(comment.getAuthor().getFirstName());
        dto.setCreatedAt(comment.getCreationDateTime());
        dto.setPk(comment.getId());
        dto.setText(comment.getText());
        return dto;
    }
}
