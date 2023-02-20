package ru.skypro.homework.dto;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.*;
import lombok.experimental.FieldDefaults;

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
   * почта пользователя
   */
  String email;
  /**
   * Имя пользователя
   */
  String firstName;
  /**
   * id пользователя
   */
  Integer id;
  /**
   * Фамилия пользователя
   */
  String lastName;
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

}
