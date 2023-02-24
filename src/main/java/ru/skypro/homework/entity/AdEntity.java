package ru.skypro.homework.entity;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.*;
import lombok.experimental.FieldDefaults;
import org.hibernate.Hibernate;

import javax.persistence.*;
import java.util.List;
import java.util.Objects;


/**
 * Сущность объявления
 */
@Getter
@Setter
@ToString
@AllArgsConstructor
@NoArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE)
@Table(name = "ads")
@Entity
public class AdEntity {
  /** Id Объявления
   * @param id  */
  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  @Column(name = "id", nullable = false)
  Integer id;
  /** Автор Объявления
   * @param author  */
  @ManyToOne
  @JsonIgnore
  @JoinColumn(name = "author_id")
  UserEntity author;
  /** Стоимость Объявления
   * @param price  */
  @Column(name = "price")
  Integer price;
  /** Заголовок Объявления
   * @param title  */
  @Column(name = "title")
  String title;
  /** Описание Объявления
   * @param description  */
  @Column(name = "description")
  String description;
  /** Комментарии Объявления
   */
  @OneToMany(mappedBy = "ad")
  @JsonBackReference
  @ToString.Exclude
  List<CommentEntity> commentEntities;
  /** Фото Объявления
    */
  @OneToMany(mappedBy = "ad")
  @JsonBackReference
  @ToString.Exclude
  List<ImageEntity> imageEntities;

  @Override
  public boolean equals(Object o) {
    if (this == o) return true;
    if (o == null || Hibernate.getClass(this) != Hibernate.getClass(o)) return false;
    AdEntity adEntity = (AdEntity) o;
    return id != null && Objects.equals(id, adEntity.id);
  }

  @Override
  public int hashCode() {
    return getClass().hashCode();
  }
}
