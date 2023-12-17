package ru.skypro.homework.model;

import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.CreationTimestamp;

import javax.persistence.*;
import java.time.LocalDateTime;

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
    @Column(name = "comment_id")
    private int pk;

    /**
     * Внешний ключ: ID автора из таблицы 'users'
     * @see User
     */
    @ManyToOne
    @JoinColumn(name = "author_id")
    private User author;

    /**
     * Внешний ключ: ссылка на аватар автора из таблицы 'images'
     * @see Image
     */
    @OneToOne
    @JoinColumn(name = "image_id")
    private Image authorImage;

    /**
     * Внешний ключ: ссылка на объявления из 'ads'
     * @see Ad
     */
    @ManyToOne
    @JoinColumn(name = "ad_id", nullable = false)
    private Ad ad;

    /**
     * Имя автора комментария
     */
    @Column(name = "author_name")
    private String authorFirstName;

    /**
     * Дата и время создания комментария в миллисекундах с 00:00:00 01.01.1970
     */
    @CreationTimestamp
    @Column(name = "comment_date")
    private LocalDateTime createdAt;

    /**
     * Текст комментария
     */
    @Column(name = "comment_text")
    private String text;
}
