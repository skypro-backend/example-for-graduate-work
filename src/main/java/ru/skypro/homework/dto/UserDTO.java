package ru.skypro.homework.dto;

import java.time.LocalDateTime;
import java.util.List;
import lombok.*;
import lombok.experimental.FieldDefaults;
import ru.skypro.homework.entity.AdEntity;
import ru.skypro.homework.entity.CommentEntity;

/**
 * DTO сущности
 */
@Getter
@Setter
@ToString
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode
@FieldDefaults(level = AccessLevel.PRIVATE)
public class UserDTO {

  /**
   * id пользователя
   */
  Integer id;
  /**
   * Имя пользователя
   */
  String firstName;
  /**
   * Фамилия пользователя
   */
  String lastName;
  /**
   * почта пользователя
   */
  String email;
  /**
   * телефон пользователя
   */
  String phone;
  /**
   * дата регистрации пользователя
   */
  LocalDateTime regDate;
  /**
   * город пользователя
   */
  String city;
  /**
   * фото пользователя
   */
  String image;

  List<AdEntity> adEntities;

  List<CommentEntity> commentEntities;

}
