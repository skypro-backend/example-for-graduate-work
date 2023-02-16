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
public class CommentDTO {
  //  Integer id;
  /**Id автора комментария */
  Integer author;
  String createdAt;
  Integer pk;
  /**Текст комментария */
  String text;

}
