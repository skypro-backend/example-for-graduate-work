package ru.skypro.homework.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.CreationTimestamp;

import javax.persistence.*;
import java.time.Instant;

/**
 * Класс, описывающий комментарий
 */
@Entity
@Table (name = "comment")
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Comment {
     @ManyToOne(fetch = FetchType.LAZY)
     @JoinColumn(name = "ad_id")
     private Ad ad;

     @Id
      @GeneratedValue (strategy = GenerationType.IDENTITY)
      @Column (nullable = false)
      private Integer id;

      @ManyToOne(fetch = FetchType.LAZY)
      @JoinColumn(name = "author_id")
      private User author;

      @JoinColumn(name = "authorImage")
      private String authorImage;

      @Column(name = "authorFirstName", length = 32)
      private String authorFirstName;

      // для автоматической установки метки времени при первом сохранении
      @CreationTimestamp
      private Instant createdAt;

      @Column(name = "text", nullable = false, length = 1000)
      private String text;

}
