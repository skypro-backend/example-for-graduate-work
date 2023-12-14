package ru.skypro.homework.model;

import lombok.*;

import javax.persistence.*;

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
//    @ManyToOne
    @Column(name = "author_id")
    private int author;

    /**
     * Внешний ключ: ссылка на фото из таблицы 'Avatar'
     * @see Avatar
     */
//    @OneToOne
    @Column(name = "image_id")
    private String image;

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
     * Внешний ключ: ID комментария из таблицы 'comments'
     * @see Comment
     */
//    @OneToMany
    @Column(name = "comment_id")
    private int comment;
}