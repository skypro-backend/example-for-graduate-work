package ru.skypro.homework.dto;


import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.ToString;
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

  //Id автора комментария
  int author;
  //Дата создания комментария
  String createdAt;
  //Id объявления
  int pk;
  //Текст комментария
  String text;
}
