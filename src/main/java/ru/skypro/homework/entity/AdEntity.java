package ru.skypro.homework.entity;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.*;
import lombok.experimental.FieldDefaults;

import javax.persistence.*;
import java.util.List;


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
  int pk;

  @ManyToOne
  @JsonIgnore
  @JoinColumn(name = "author_id")
  UserEntity author;

  @Column(name = "price")
  int price;

  @Column(name = "title")
  String title;

  @Column(name = "description")
  String description;

  @OneToMany(mappedBy = "pk")
  @JsonBackReference
  List<CommentEntity> commentEntities;

  @OneToMany(mappedBy = "ad")
  @JsonBackReference
  List<ImageEntity> imageEntities;

}
