package ru.skypro.homework.dto;


import com.fasterxml.jackson.annotation.JsonIgnore;
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


  @JsonIgnore
  Integer id;
  /**Id автора комментария */
  Integer author;
  /**Дата создания комментария  */
  String createdAt;
  /**Id объявления  */
  Integer pk;
  /**Текст комментария */
  String text;

}
