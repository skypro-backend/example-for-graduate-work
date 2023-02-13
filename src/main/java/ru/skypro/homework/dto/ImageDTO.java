package ru.skypro.homework.dto;

import lombok.*;
import lombok.experimental.FieldDefaults;


/**
 * DTO  для {@link ru.skypro.homework.entity.ImageEntity} картинки
 */
@Getter
@Setter
@ToString
@AllArgsConstructor
@NoArgsConstructor
@EqualsAndHashCode
@FieldDefaults(level = AccessLevel.PRIVATE)
public class ImageDTO {

    String image;

}
