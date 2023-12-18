package ru.skypro.homework.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.util.Collection;
/**
 * Класс {@code AdEntity} представляет объявление в системе.
 * Аннотации JPA, Lombok и отношения между сущностями используются
 * для упрощения хранения информации в базе данных.
 *
 * <p>Поля класса:</p>
 * <ul>
 *     <li>{@code id} - уникальный идентификатор</li>
 *     <li>{@code image} - URL или путь изображения</li>
 *     <li>{@code price} - цена объявления</li>
 *     <li>{@code title} - заголовок объявления</li>
 *     <li>{@code description} - описание объявления</li>
 *     <li>{@code author} - автор объявления (связь ManyToOne)</li>
 *     <li>{@code comments} - коллекция комментариев к объявлению (связь OneToMany)</li>
 * </ul>
 *
 * @author Michail Z. (GH: HeimTN)
 */
@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "ads")
public class AdEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;
    private String image;
    private int price;
    private String title;
    private String description;
    @ManyToOne
    @JoinColumn(name = "author_id")
    private UserEntity author;
    @OneToMany(mappedBy = "ad", cascade = CascadeType.ALL)
    @JoinColumn(name = "comments_id")
    private Collection<CommentEntity> comments;
}
