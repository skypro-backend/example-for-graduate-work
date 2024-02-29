package ru.skypro.sitesforresaleofthings.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.time.Instant;
import java.time.LocalDateTime;

/**
 * Создаем сущность "Комментарий"
 */
@Entity
/**
 * Создаем таблицу comments(Комментарии), имеющую следующие свойства-колонки:
 * 1) pk - id комментария,
 * 2) author - id автора комментария,
 * 3) text - текст комментария,
 * 4) ad - объявление комментария,
 * 5) createdAt - дата и время создания комментария в миллисекундах с 00:00:00 01.01.1970
 */
@Table(name = "comments")
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Comment {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_pk_comment")
    private Integer pk;

    @ManyToOne
    @JoinColumn(name = "author_id")
    private User author;

    @Column(name = "text_comment")
    private String text;

    @ManyToOne
    @JoinColumn(name = "ad_id_pk")
    private Ad ad;

    @Column(name = "created_at")
    private Instant createdAt;
}