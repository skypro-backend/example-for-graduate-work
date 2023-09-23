package ru.skypro.homework.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
import ru.skypro.homework.entity.Comment;
import ru.skypro.homework.repository.UserRepository;

@AllArgsConstructor
@NoArgsConstructor
@EqualsAndHashCode
@Data
public class CommentDto {

    private Integer author; // ($int32) id автороа комментария
    private String authorImage; // ссылка на аватар автора комментария
    private String authorFirstName; // имя создателя комментария
    private Integer createdAt; //($int32) дата и время создания комментария в миллисекундах с 00:00:00 01.01.1970
    private Integer pk; //($int32) id комментария
    private String text; // текст комментария
    UserRepository userRepository;

    public static CommentDto fromComment(Comment comment) {
        CommentDto dto = new CommentDto();
        dto.setPk(comment.getPk());
        dto.setAuthor(comment.getAuthor().getId());
        dto.setAuthorImage(comment.getAuthorImage());
        dto.setAuthorFirstName(comment.getAuthorFirstName());
        dto.setCreatedAt(comment.getCreatedAt());
        dto.setText(comment.getText());

        return dto;
    }


    public Comment toComment() {
        Comment comment = new Comment();
        comment.setPk(this.getPk());
        comment.setAuthor(userRepository.findById(this.getAuthor()).orElseThrow());
        comment.setAuthorImage(this.getAuthorImage());
        comment.setAuthorFirstName(this.getAuthorFirstName());
        comment.setCreatedAt(this.getCreatedAt());
        comment.setText(this.getText());


        return comment;
    }
}
