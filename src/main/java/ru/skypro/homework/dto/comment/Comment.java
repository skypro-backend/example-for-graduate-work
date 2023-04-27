package ru.skypro.homework.dto.comment;

import lombok.Data;
import ru.skypro.homework.model.CommentModel;

import java.time.Instant;

@Data
public class Comment {
    private Integer pk;
    private Integer author;
    private String authorImage;
    private String authorFirstName;
    private Instant createdAt;
    private String text;

    public static Comment fromModel( CommentModel model ) {
        Comment comment = new Comment();
        comment.setAuthor(model.getUser().getPk());
        comment.setAuthorImage("/users/avatar/" + model.getUser().getPk());
        comment.setAuthorFirstName(model.getUser().getFirstName());
        comment.setCreatedAt(model.getCreatedAt());
        comment.setPk(model.getId());
        comment.setText(model.getText());
        return comment;
    }

    public CommentModel toModel() {
        CommentModel model = new CommentModel();
        model.setId(this.getPk());
        model.setText(this.getText());
        model.setCreatedAt(this.getCreatedAt());
        return model;
    }
}
