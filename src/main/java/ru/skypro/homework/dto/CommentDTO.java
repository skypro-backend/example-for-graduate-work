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

  /**Id автора комментария */
  Integer author;
  String createdAt;
  //Id комментария
  Integer pk;
  /**Текст комментария */
  String text;

}
