package ru.skypro.homework.dto;

import lombok.*;
import lombok.experimental.FieldDefaults;
import ru.skypro.homework.entity.AdEntity;
import ru.skypro.homework.entity.CommentEntity;

import java.util.List;

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
  String regDate;
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
