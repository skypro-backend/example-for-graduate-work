package ru.skypro.homework.model;


import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;

/**
 * Сущность для таблицы "ads".
 * Хранит данные объявления в базе данных.
 * Основные функции:
 * - хранение информации об объявлении (заголовок, цена, описание);
 * - связь с автором объявления (UserEntity);
 * - связь с картинкой объявления (ImageEntity).
 */
@Entity
@Table(name = "ads")
@Data
@NoArgsConstructor
public class AdEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @Column(length = 32, nullable = false)
    private String title;

    @Column(nullable = false)
    private Integer price;

    @Column(length = 64)
    private String description;

    @OneToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "image_id")
    private ImageEntity image;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "author_id", nullable = false)
    private UserEntity author;


}
