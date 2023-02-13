package ru.skypro.homework.entity;


import lombok.*;
import lombok.experimental.FieldDefaults;


/**
 * Сущность картинки
 */
@Getter
@Setter
@ToString
@AllArgsConstructor
@NoArgsConstructor
@EqualsAndHashCode
@FieldDefaults(level = AccessLevel.PRIVATE)
public class ImageEntity {

    String image;

}
