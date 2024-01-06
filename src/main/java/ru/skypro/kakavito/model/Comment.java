package ru.skypro.kakavito.model;

import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.time.LocalDateTime;

/**
 * Создание сущности Comment
 */
@Entity
@Data
@NoArgsConstructor
@Table(name = "comments")
public class Comment {

    /**
     * ID комментария
     */
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    public Integer id;

    /**
     * Внешний ключ: ID автора из таблицы 'users'
     *
     * @see User
     */
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "author_id")
    private User user;

    /**
     * Внешний ключ: ссылка на объявления из 'ads'
     *
     * @see Ad
     */
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "ad_id", referencedColumnName = "id")
    private Ad ad;

    /**
     * Дата и время создания комментария в миллисекундах с 00:00:00 01.01.1970
     */
    public LocalDateTime createdAt;

    /**
     * Текст комментария
     */
    public String text;
}
