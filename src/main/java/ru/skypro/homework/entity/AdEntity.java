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

  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  @Column(name = "id", nullable = false)
  Integer id;

  @ManyToOne(cascade = {CascadeType.ALL})
  @JsonIgnore
  @JoinColumn(name = "author_id")
  UserEntity author;

  @Column(name = "price")
  Integer price;

  @Column(name = "title")
  String title;

  @Column(name = "description")
  String description;

  @OneToMany(mappedBy = "ad")
  @JsonBackReference
  @ToString.Exclude
  List<CommentEntity> commentEntities;

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
