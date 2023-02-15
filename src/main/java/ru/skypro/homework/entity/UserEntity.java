package ru.skypro.homework.entity;

import com.fasterxml.jackson.annotation.JsonBackReference;
import lombok.*;
import lombok.experimental.FieldDefaults;
import org.hibernate.Hibernate;

import javax.persistence.*;
import java.time.LocalDateTime;
import java.util.List;
import java.util.Objects;

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
  Integer id;

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
  String image;

  /**
   * Список объявлений пользователя
   */
  @OneToMany(mappedBy = "author", fetch = FetchType.LAZY)
  @JsonBackReference
  @ToString.Exclude
  List<AdEntity> adEntities;

  @OneToMany(mappedBy = "author")
  @JsonBackReference
  @ToString.Exclude
  List<CommentEntity> commentEntities;

  @Override
  public boolean equals(Object o) {
    if (this == o) return true;
    if (o == null || Hibernate.getClass(this) != Hibernate.getClass(o)) return false;
    UserEntity that = (UserEntity) o;
    return id != null && Objects.equals(id, that.id);
  }

  @Override
  public int hashCode() {
    return getClass().hashCode();
  }
}
