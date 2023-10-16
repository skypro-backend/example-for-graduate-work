package ru.skypro.homework.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import javax.persistence.*;

/**
 * Класс, описывающий объявление
 */
@Entity // сущность
@Table (name = "ad_table")
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Ad {

      @Id
      @GeneratedValue(strategy = GenerationType.IDENTITY)
      private Integer id;

      @ManyToOne(fetch = FetchType.LAZY)
      @JoinColumn(name = "author_id", nullable = false)
      private User author;

      @Column(name = "image")
      private String image;

      @Column(name = "price")
      private Integer price;

      @Column(name = "title")
      private String title;

      @Column(name = "description")
      private String description;

      @Override
      public String toString() {
            return "Ad{" +
                    "id=" + id +
                    ", author=" + author +
                    ", image='" + image + '\'' +
                    ", price=" + price +
                    ", title='" + title + '\'' +
                    ", description='" + description + '\'' +
                    '}';
      }
}
