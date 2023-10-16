package ru.skypro.homework.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.Comment;

import javax.persistence.*;
import java.util.Collection;
import java.util.Collections;
import java.util.List;

/**
 * Сущность объявления.
 */
@Entity
@Data
@EqualsAndHashCode
@NoArgsConstructor
@AllArgsConstructor
@Table(name="ads")

     public class Ad {
     /**
      * Id объявления
      */
     @Id
     @GeneratedValue(strategy = GenerationType.IDENTITY)
     @Column(name="id")

     private Integer id;

     /**
      * Цена объявления
      */
     @Column(name="price")
     private Integer price;
     /**
      * Заголовок объявления
     */
     @Column(name="title")
     private String title;
     /**
      * Описание объявления
      */
    @Column(name="description")
    private String description;

    /**
     * Картинка объявления
     */
    @Column(name="image_path")
    private String imagePath;
    /**
     * Автор объявления
     */
    @Column(name="author_id")
    private String userAuthor;

    @ManyToOne
    private User user;



}
