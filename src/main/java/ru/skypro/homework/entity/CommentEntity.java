package ru.skypro.homework.entity;


import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.*;
import lombok.experimental.FieldDefaults;
import org.hibernate.Hibernate;

import javax.persistence.*;
import java.time.LocalDateTime;
import java.util.Objects;

/**
 * Сущность  комментариев
 */
@Entity
@Table(name = "comments")
@Getter
@Setter
@ToString
@NoArgsConstructor
@AllArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE)
public class CommentEntity {
  /**Id автора комментария
   * @param id */
  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  @Column(name = "id", nullable = false)
  Integer id;

  /**Id автора комментария
   * @param author */
  @ManyToOne
  @JsonIgnore
  @JoinColumn(name = "author_id")
  UserEntity author;

  /**Дата создания комментария
   * @param createdAt  */
  @Column(name = "created_at")
  LocalDateTime createdAt;

  /**Id объявления
   *  @param ad  */
  @ManyToOne
  @JsonIgnore
  @JoinColumn(name = "pk_ads")
  AdEntity ad;

  /**Текст комментария
   *  @param text  */
  @Column(name = "text")
  String text;

  @Override
  public boolean equals(Object o) {
    if (this == o) return true;
    if (o == null || Hibernate.getClass(this) != Hibernate.getClass(o)) return false;
    CommentEntity that = (CommentEntity) o;
    return id != null && Objects.equals(id, that.id);
  }

  @Override
  public int hashCode() {
    return getClass().hashCode();
  }
}
