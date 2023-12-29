package ru.skypro.homework.model;

import lombok.*;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

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
    @Column(name = "ad_id")
    private int pk;

    /**
     * Внешний ключ: ID автора из таблицы 'users'
     * @see User
     */
    @ManyToOne
    @JoinColumn(name = "author_id")
    private User author;

    /**
     * Внешний ключ: ссылка на фото из таблицы 'images'
     * @see Image
     */
    @OneToOne
    @JoinColumn(name = "image_id")
    private Image image;

    /**
     * Цена объявления
     */
    @Column(name = "price")
    private int price;

    /**
     * Заголовок объявления
     */
    @Column(name = "title")
    private String title;

    /**
     * Описание товара
     */
    @Column
    private String description;

    /**
     * Внешний ключ: комментарии к объявлению из таблицы 'comments'
     * @see Comment
     */
    @OneToMany(mappedBy = "ad", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<Comment> comments = new ArrayList<>();
}