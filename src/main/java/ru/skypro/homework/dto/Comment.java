package ru.skypro.homework.dto;

import lombok.*;
import lombok.experimental.FieldDefaults;

/**
 * DTO для комментариев
 */
@Getter
@ToString
@AllArgsConstructor
@EqualsAndHashCode
@FieldDefaults(level = AccessLevel.PRIVATE)
public class Comment {
    //Id автора комментария
    int authorId;
    //Дата создания комментария
    String createdAt;
    //Id объявления
    int adsPk;
    //Текст комментария
    String text;
}
