package ru.skypro.homework.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
import ru.skypro.homework.entity.Comment;

import java.time.LocalDateTime;
import java.util.TimeZone;


@AllArgsConstructor
@NoArgsConstructor
@EqualsAndHashCode
@Data
public class CommentDto {

    private Integer author; // ($int32) id автороа комментария
    private String authorImage; // ссылка на аватар автора комментария
    private String authorFirstName; // имя создателя комментария
    private LocalDateTime createdAt; //($int64) дата и время создания комментария в миллисекундах с 00:00:00 01.01.1970
    private Integer pk; //($int32) id комментария
    private String text; // текст комментария

    public static CommentDto fromComment(Comment comment) {
        TimeZone tz = TimeZone.getDefault();
        LocalDateTime ldt = LocalDateTime.ofInstant(comment.getCreatedAt(), tz.toZoneId());
        CommentDto dto = new CommentDto();
        dto.setPk(comment.getPk());
        dto.setAuthor(comment.getUser().getId());
        if (comment.getUserImage() != null) {
            dto.setAuthorImage(String.format("/ads/image/%s", comment.getUserImage()));
        } else {
            dto.setAuthorImage(null);
        }
        dto.setAuthorFirstName(comment.getUserFirstName());
        dto.setCreatedAt(ldt);
        dto.setText(comment.getText());

        return dto;
    }


    public Comment toComment() {
        Comment comment = new Comment();
        comment.setPk(this.getPk());
        comment.setUserImage(this.getAuthorImage());
        comment.setUserFirstName(this.getAuthorFirstName());
        comment.setText(this.getText());


        return comment;
    }
}
