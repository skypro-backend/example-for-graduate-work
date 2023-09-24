package ru.skypro.homework.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import javax.persistence.*;

@Entity // сущность
@Table (name = "ads")
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Ads {

      @Id
      @GeneratedValue (strategy = GenerationType.IDENTITY)
      @Column (name = "id", nullable = false)
      private Integer id;

      @Column(name = "title")
      private String title;

      @Column(name = "price")
      private Integer price;

}
