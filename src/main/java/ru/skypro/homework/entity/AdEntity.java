package ru.skypro.homework.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.Data;

import javax.persistence.*;

import java.util.List;


/**
 * AdEntity- сущность
 * <br><i>содержит поля:</i>
 * <br>- id<i> (id объявления)</i>
 * <br>- name<i> (заголовок объявления)</i>
 * <br>- price<i> (цена объявления)</i>
 * <br>- description<i> (описание объявления)</i>
 * <br>- author<i> (связь {@link AdEntity} и {@link UserEntity})</i>
 * <br>- comments<i> (связь {@link AdEntity} и {@link CommentEntity})</i>
 * <br>- image<i> (фото объявления, связь {@link AdEntity} и {@link ImageEntity})</i>
 */
@Entity(name = "ads")
@Data
public class AdEntity {
    /**
     * id
     */
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    /**
     * заголовок объявления
     */
    private String name;//title

    /**
     * цена объявления
     */
    private Double price;

    /**
     * описание объявления
     */
    private String description;

    /**
     * связь {@link AdEntity} и {@link UserEntity}
     * <br><i>много объявлений - один пользователь</i>
     */
    @ManyToOne
    @JsonIgnore
    @JoinColumn(name = "author_id", nullable = false)
    private UserEntity author;

    /**
     * связь {@link AdEntity} и {@link CommentEntity}
     * <br><i>одно  объявление - много комментариев</i>
     */
    @OneToMany(mappedBy = "pk", cascade = CascadeType.ALL)
    @JsonIgnore
    private List<CommentEntity> comments;

    /**
     * фото объявления
     */
    @OneToOne
    @JsonIgnore
    @JoinColumn(name = "image")
    private ImageEntity image;
}
