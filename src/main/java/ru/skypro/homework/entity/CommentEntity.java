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
import lombok.ToString;
import lombok.experimental.FieldDefaults;

import java.util.Objects;

/**
 * DTO для комментариев
 */
@Entity
@Getter
@ToString
@NoArgsConstructor
@AllArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE)
public class CommentEntity {

  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  @Column(name = "id", nullable = false)
  private Long id;

  /**Id автора комментария */
  int author;
  /**Дата создания комментария  */
  String createdAt;
  /**Id объявления          */
  int pk;
  /**Текст комментария */
  String text;

  @Override
  public boolean equals(Object o) {
    if (this == o) return true;
    if (!(o instanceof CommentEntity)) return false;
    CommentEntity that = (CommentEntity) o;
    return getAuthor() == that.getAuthor() && getPk() == that.getPk() && Objects.equals(getId(), that.getId()) && Objects.equals(getCreatedAt(), that.getCreatedAt()) && Objects.equals(getText(), that.getText());
  }

  @Override
  public int hashCode() {
    return Objects.hash(getId(), getAuthor(), getCreatedAt(), getPk(), getText());
  }
}
