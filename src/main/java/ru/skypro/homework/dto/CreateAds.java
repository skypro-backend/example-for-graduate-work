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
public class CreateAds {

    /**
     * Описание продаваемого объекта
     */
    String description;

    /**
     * цена
     */
    Integer price;

    /**
     * Название
     */
    String title;


}
