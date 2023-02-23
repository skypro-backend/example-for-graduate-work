package ru.skypro.homework.dto;

import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;
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
