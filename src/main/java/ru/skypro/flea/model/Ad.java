package ru.skypro.flea.model;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;

@Entity
@Table(name = "ads")
@NoArgsConstructor
@Getter
@Setter
public class Ad {
  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  @Column(name = "id", nullable = false)
  private Integer id;

  @Column(name = "title", nullable = false)
  private String title;

  @Column(name = "description")
  private String description;

  @Column(name = "price",  nullable = false)
  private Integer price;

  @Column(name = "image",  nullable = false)
  private String image;

  @ManyToOne
  @JoinColumn(name = "author_id")
  private User user;

}
