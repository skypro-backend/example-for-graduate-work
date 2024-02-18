package ru.skypro.homework.entity;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import java.time.LocalDateTime;

/**
 * CommentEntity - сущность
 * <br><i>содержит следующие поля</i>
 * <br>- author<i>(id автора комментария)</i>
 * <br>- authorImage<i>(ссылка на аватар автора комментария)</i>
 * <br>- authorFirstName<i>(имя создателя комментария)</i>
 * <br>- createAt<i>(дата и время создания комментария в милисекундах с 00:00:00 01.01.1970)</i>
 * <br>- pk<i>(id комментария)</i>
 * <br>- text<i>(текст комментария)</i>
 */
@Entity
@Getter
@Setter
@Table(name = "comments")
public class CommentEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer author;

    private String authorImage;

    private String authorFirstName;

    private LocalDateTime createAt;

    private Integer pk;

    private String text;
}
