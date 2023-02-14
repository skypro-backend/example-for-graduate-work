package ru.skypro.homework.entity;

import com.fasterxml.jackson.annotation.JsonBackReference;
import lombok.*;
import lombok.experimental.FieldDefaults;

import javax.persistence.*;
import java.time.LocalDateTime;
import java.util.Arrays;
import java.util.List;

/**
 * Сущность пользователя
 */
@Getter
@Setter
@ToString
@NoArgsConstructor
@AllArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE)
@Table(name = "users")
@Entity
public class UserEntity {

  /**
   * id пользователя
   */
  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  @Column(name = "id", nullable = false)
  int id;

  /**
   * Имя пользователя
   */
  @Column(name = "first_name")
  String firstName;

  /**
   * Фамилия пользователя
   */
  @Column(name = "last_name")
  String lastName;

  /**
   * почта пользователя
   */
  @Column(name = "email")
  String email;

  /**
   * телефон пользователя
   */
  @Column(name = "phone")
  String phone;

  /**
   * дата регистрации пользователя
   */
  @Column(name = "reg_date")
  LocalDateTime regDate;

  /**
   * город пользователя
   */
  @Column(name = "city")
  String city;

  /**
   * фото пользователя
   */
  @Column(name = "image")
  byte[] image;

  /**
   * Список объявлений пользователя
   */
  @OneToMany(mappedBy = "author", fetch = FetchType.LAZY)
  @JsonBackReference
  List<AdEntity> adEntities;

  @OneToMany(mappedBy = "author")
  @JsonBackReference
  List<CommentEntity> commentEntities;

  @Override
  public boolean equals(Object o) {
    if (this == o) return true;
    if (o == null || getClass() != o.getClass()) return false;

    UserEntity that = (UserEntity) o;

    if (id != that.id) return false;
    if (firstName != null ? !firstName.equals(that.firstName) : that.firstName != null) return false;
    if (lastName != null ? !lastName.equals(that.lastName) : that.lastName != null) return false;
    if (email != null ? !email.equals(that.email) : that.email != null) return false;
    if (phone != null ? !phone.equals(that.phone) : that.phone != null) return false;
    if (regDate != null ? !regDate.equals(that.regDate) : that.regDate != null) return false;
    if (city != null ? !city.equals(that.city) : that.city != null) return false;
    if (!Arrays.equals(image, that.image)) return false;
    if (adEntities != null ? !adEntities.equals(that.adEntities) : that.adEntities != null) return false;
    return commentEntities != null ? commentEntities.equals(that.commentEntities) : that.commentEntities == null;
  }

  @Override
  public int hashCode() {
    int result = id;
    result = 31 * result + (firstName != null ? firstName.hashCode() : 0);
    result = 31 * result + (lastName != null ? lastName.hashCode() : 0);
    result = 31 * result + (email != null ? email.hashCode() : 0);
    result = 31 * result + (phone != null ? phone.hashCode() : 0);
    result = 31 * result + (regDate != null ? regDate.hashCode() : 0);
    result = 31 * result + (city != null ? city.hashCode() : 0);
    result = 31 * result + Arrays.hashCode(image);
    result = 31 * result + (adEntities != null ? adEntities.hashCode() : 0);
    result = 31 * result + (commentEntities != null ? commentEntities.hashCode() : 0);
    return result;
  }
}
