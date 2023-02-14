package ru.skypro.homework.entity;


import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.*;
import lombok.experimental.FieldDefaults;

import javax.persistence.*;
import java.time.LocalDateTime;
import java.util.Objects;

/**
 * DTO для комментариев
 */
@Entity
@Table(name = "comments")
@Getter
@ToString
@NoArgsConstructor
@AllArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE)
public class CommentEntity {

  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  @Column(name = "id", nullable = false)
  private int id;

  /**Id автора комментария */
  @ManyToOne
  @JsonIgnore
  @JoinColumn(name = "author_id")
  UserEntity author;

  /**Дата создания комментария  */
  @Column(name = "created_at")
  LocalDateTime createdAt;

  /**Id объявления          */
  @ManyToOne
  @JsonIgnore
  @JoinColumn(name = "pk_ads")
  AdEntity pk;

  /**Текст комментария */
  @Column(name = "text")
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
