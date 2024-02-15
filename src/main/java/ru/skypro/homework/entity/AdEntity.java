package ru.skypro.homework.entity;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;

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
    @GeneratedValue
    private int author;
    private String image;
    @Id
    @GeneratedValue
    private int pk;
    private int price;
    private String title;
}
