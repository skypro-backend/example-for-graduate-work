package ru.skypro.kakavito.model;

import lombok.*;

import javax.persistence.*;
import java.util.HashSet;
import java.util.Set;

/**
 * The Ad class represents the essence of the ad in the database.
 * It stores information about the ad,
 * including its unique identifier (id), the id of the author of the ad, the link to the image of the ad,
 * the ad id, the price of the ad and the title.
 */

@Entity
@Data
@NoArgsConstructor
@Table(name = "ads")
public class Ad {

    /**
     * ID объявления
     */
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    /**
     * Внешний ключ: ID автора из таблицы 'users'
     *
     * @see User
     */
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "author_id", nullable = false)
    private User user;

    /**
     * Внешний ключ: ссылка на фото из таблицы 'images'
     *
     * @see Image
     */
    @OneToOne(fetch = FetchType.LAZY, cascade = CascadeType.ALL, orphanRemoval = true)
    @JoinColumn(name = "image_id", referencedColumnName = "id")
    private Image image;

    /**
     * Цена объявления
     */
    @Column(nullable = false)
    private Integer price;

    /**
     * Заголовок объявления
     */
    @Column(nullable = false, length = 100)
    private String title;

    /**
     * Описание товара
     */
    @Column(nullable = false)
    private String description;

    /**
     * Внешний ключ: комментарии к объявлению из таблицы 'comments'
     *
     * @see Comment
     */
    @OneToMany(mappedBy = "ad", cascade = CascadeType.ALL, orphanRemoval = true)
    private Set<Comment> comments = new HashSet<>();
}