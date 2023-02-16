package ru.skypro.homework.dto;


import lombok.*;
import lombok.experimental.FieldDefaults;

import java.util.List;

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
    String authorFirstName;


    /**
     * Фамилия пользователя
     */
    String authorLastName;

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
    List<String> image;

    /**
     * телефон пользователя
     */
    String phone;

    /**
     * id пользователя
     */
    Integer pk;

    /**
     * цена
     */
    Integer price;

    /**
     * Название
     */
    String title;

}
