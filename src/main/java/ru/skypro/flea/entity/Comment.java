package ru.skypro.flea.entity;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import ru.skypro.flea.converter.LocalDateTimeConverter;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import java.time.LocalDateTime;

@Entity
@Table(name = "comments")
@NoArgsConstructor
@Getter
@Setter
public class Comment {
  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  @Column(name = "id", nullable = false)
  private Integer id;

  @Column(name = "text", nullable = false)
  private String title;

  @Column(name = "pubic_date")
  @Convert(converter = LocalDateTimeConverter.class)
  @NotNull
  private LocalDateTime pubicDate;

  @ManyToOne
  @JoinColumn(name = "ads_id")
  private Ad ad;

  @ManyToOne
  @JoinColumn(name = "author_id")
  private User user;

}
