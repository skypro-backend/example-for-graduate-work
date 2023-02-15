package ru.skypro.homework.dto;


import java.time.LocalDateTime;
import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.ToString;
import lombok.experimental.FieldDefaults;
import ru.skypro.homework.entity.AdEntity;
import ru.skypro.homework.entity.UserEntity;

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
  Integer id;
  UserEntity author;
  LocalDateTime createdAt;
  AdEntity pk;
  /**Текст комментария */
  String text;

}
