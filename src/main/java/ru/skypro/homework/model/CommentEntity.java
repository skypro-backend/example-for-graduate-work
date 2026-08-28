package ru.skypro.homework.model;

import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;

/**
 * Сущность для таблицы "comments".
 * Хранит данные комментария в базе данных.
 * Основные функции:
 * - хранение текста комментария и времени создания;
 * - связь с автором комментария (UserEntity);
 * - связь с объявлением, к которому оставлен комментарий (AdEntity).
 */
@Entity
@Table(name = "comments")
@Data
@NoArgsConstructor
public class CommentEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @Column(length = 64, nullable = false)
    private String text;

    @Column(nullable = false)
    private Long createdAt;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "author_id", nullable = false)
    private UserEntity author;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "ad_id", nullable = false)
    private AdEntity ad;
}
