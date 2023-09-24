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

    private Integer user; // ($int32) id автороа комментария
    private String userImage; // ссылка на аватар автора комментария
    private String userFirstName; // имя создателя комментария
    private LocalDateTime createdAt; //($int32) дата и время создания комментария в миллисекундах с 00:00:00 01.01.1970
    private Integer pk; //($int32) id комментария
    private String text; // текст комментария

    public static CommentDto fromComment(Comment comment) {
//        TimeZone tz = TimeZone.getDefault();
//        LocalDateTime ldt = LocalDateTime.ofInstant(comment.getCreatedAt(), tz.toZoneId());
        CommentDto dto = new CommentDto();
        dto.setPk(comment.getPk());
        dto.setUser(comment.getUser().getId());
        dto.setUserImage(comment.getUserImage());
        dto.setUserFirstName(comment.getUserFirstName());
//        dto.setCreatedAt(ldt);
        dto.setText(comment.getText());

        return dto;
    }


    public Comment toComment() {
        Comment comment = new Comment();
        comment.setPk(this.getPk());
        comment.setUserImage(this.getUserImage());
        comment.setUserFirstName(this.getUserFirstName());
//        comment.setCreatedAt(this.getCreatedAt());
        comment.setText(this.getText());


        return comment;
    }
}
