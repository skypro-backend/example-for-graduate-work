package ru.skypro.flea.model;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
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
  private String text;

  @Column(name = "public_date")
  private LocalDateTime publicDate;

  @ManyToOne
  @JoinColumn(name = "ads_id")
  private Ad ad;

  @ManyToOne
  @JoinColumn(name = "author_id")
  private User user;

  @PrePersist
  private void onCreate() {
    publicDate = LocalDateTime.now();
  }
}
