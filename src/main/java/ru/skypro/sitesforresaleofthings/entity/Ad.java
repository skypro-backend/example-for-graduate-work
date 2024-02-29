package ru.skypro.sitesforresaleofthings.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.util.Collection;

/**
 * Создаем сущность "Объявление"
 */
@Entity
/**
 * Создаем таблицу ads(Объявления), имеющую следующие свойства-колонки:
 * 1) pk - id объявления,
 * 2) author - id автора объявления,
 * 3) image - ссылка на картинку объявления,
 * 4) price - цена объявления,
 * 5) title - заголовок объявления,
 * 6) description - описание объявления,
 * 7) comments - комментарии объявления
 */
@Table(name = "ads")
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Ad {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_pk_ad")
    private Integer pk;

    @ManyToOne
    @JoinColumn(name = "author_id")
    private User author;

    @Column(name = "image_ad")
    private String image;

    @Column(name = "price_ad")
    private Integer price;

    @Column(name = "title_ad")
    private String title;

    @Column(name = "description_ad")
    private String description;

    @OneToMany(mappedBy = "ad")
    private Collection<Comment> comments;
}