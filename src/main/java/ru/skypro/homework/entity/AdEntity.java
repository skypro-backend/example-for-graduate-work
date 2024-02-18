package ru.skypro.homework.entity;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;
import lombok.Getter;
import lombok.Setter;
import ru.skypro.homework.dto.Ad;
import ru.skypro.homework.dto.User;

import javax.persistence.*;

import static javax.persistence.GenerationType.IDENTITY;

/**
 * AdEntity - сущность
 * <br><i>содержит следующие поля:</i>
 * <br>- author<i> (id автора)</i>
 * <br>- image<i>(ссылка на картинку объявления)</i>
 * <br>- pk<i>(id объявления)</i>
 * <br>- price<i>(цена объявления)</i>
 * <br>- title<i>(заголовок объявления)</i>
 * <br>- description<i>(описание объявления)</i>
 */
@Entity
@Getter
@Setter
@Table(name = "ads")

public class AdEntity {
    @Id
    @GeneratedValue(strategy = IDENTITY)
    private int author;
    private String image;
    private int pk;
    private int price;
    private String title;
    private String description;
}
