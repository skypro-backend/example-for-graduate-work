package ru.skypro.homework.entity;

import lombok.Data;

import javax.persistence.*;
import java.time.LocalDateTime;

/**
 * CommentEntity - сущность
 * <br><i>содержит поля</i>
 * <br>- id<i> (id комментария)</i>
 * <br>- createAt<i> (дата и время создания комментария)</i>
 * <br>- text<i> (текст комментария)</i>
 * <br>- author<i> (id автора комментария, {@link UserEntity})</i>
 * <br>- pk<i> (ссылка на id_объявления, {@link AdEntity})</i>
 */
@Entity
@Data
@Table(name = "comments")
public class CommentEntity {
    /**
     * id комментария
     */
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    /**
     * дата и время создания комментария
     */
    private LocalDateTime createAt;

    /**
     * текст комментария
     */
    private String text;

    /**
     * связь {@link AdEntity} и {@link CommentEntity}
     * <br><i>много комментариев - одно  объявление </i>
     */
    @ManyToOne
//    @JsonIgnore
    @JoinColumn(name = "ad_id", nullable = false)
    private AdEntity pk;

    /**
     * связь {@link UserEntity} и {@link CommentEntity}
     * <br><i>много комментариев - один  пользователь </i>
     */
    @ManyToOne
//    @JsonIgnore
    @JoinColumn(name = "author_id", nullable = false)
    private UserEntity author;
}
