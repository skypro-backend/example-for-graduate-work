package ru.skypro.homework.model;

import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;

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
//    @ManyToOne
    @JoinColumn(name = "author_id")
    private int author;

    /**
     * Внешний ключ: ссылка на аватар автора из таблицы 'Avatar'
     * @see Avatar
     */
    @OneToOne
    @JoinColumn(name = "image_str")
    private Avatar authorImage;

    /**
     * Имя автора комментария
     */
    @Column(name = "author_name")
    private String authorFirstName;

    /**
     * Дата и время создания комментария в миллисекундах с 00:00:00 01.01.1970
     */
    @Column(name = "created_at")
    private Long createdAt;

    /**
     * Текст комментария
     */
    @Column(name = "comment_text")
    private String text;
}
