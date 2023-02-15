package ru.skypro.homework.dto;


import lombok.*;
import lombok.experimental.FieldDefaults;

@Getter
@Setter
@ToString
@AllArgsConstructor
@NoArgsConstructor
@EqualsAndHashCode
@FieldDefaults(level = AccessLevel.PRIVATE)
public class FullAds {

    /**
     * Имя пользователя
     */
    String firstName;


    /**
     * Фамилия пользователя
     */
    String lastName;

    /**
     * Описание продаваемого объекта
     */
    String description;

    /**
     * почта пользователя
     */
    String email;

    /**
     * картинка
     */
    String image;

    /**
     * телефон пользователя
     */
    String phone;

    /**
     * id пользователя
     */
    int pk;

    /**
     * цена
     */
    int price;

    /**
     * Название
     */
    String title;

}
