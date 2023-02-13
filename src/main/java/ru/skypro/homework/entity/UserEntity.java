package ru.skypro.homework.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;
import lombok.experimental.FieldDefaults;

/**
 * Сущность пользователя
 */
@Getter
@Setter
@ToString
@NoArgsConstructor
@AllArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE)
@Entity
public class UserEntity {

  /**
   * id пользователя
   */
  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  @Column(name = "id", nullable = false)
  Long id;
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


  @Override
  public boolean equals(Object o) {
    if (this == o) {
      return true;
    }
    if (o == null || getClass() != o.getClass()) {
      return false;
    }

    UserEntity that = (UserEntity) o;

    if (!id.equals(that.id)) {
      return false;
    }
    if (!firstName.equals(that.firstName)) {
      return false;
    }
    if (!lastName.equals(that.lastName)) {
      return false;
    }
    return email.equals(that.email);
  }

  @Override
  public int hashCode() {
    int result = id.hashCode();
    result = 31 * result + firstName.hashCode();
    result = 31 * result + lastName.hashCode();
    result = 31 * result + email.hashCode();
    return result;
  }
}
