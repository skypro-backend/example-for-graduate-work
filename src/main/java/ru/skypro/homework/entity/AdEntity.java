package ru.skypro.homework.entity;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;

import javax.persistence.*;

import static javax.persistence.GenerationType.IDENTITY;

/**
 * AdEntity - сущность
 * <br><i>содержит следующие поля:</i>
 * <br>- author<i>(id автора объявления)</i>
 * <br>- image<i>(ссылка на картинку объявления)</i>
 * <br>- pk<i>(id объявления)</i>
 * <br>- price<i>(цена объявления)</i>
 * <br>- title<i>(заголовок объявления)</i>
 */
@Entity
@Data
@Table(name = "ads")

public class AdEntity {
    @Id
    @GeneratedValue(strategy = IDENTITY)
    private Long author;
    private String image;
    @Id
    @GeneratedValue(strategy = IDENTITY)
    private Long pk;
    private int price;
    private String title;
}
