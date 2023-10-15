package ru.skypro.homework.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.Type;

import javax.persistence.*;

@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
@Table (name = "images")
public class Image {
      @Id
      @GeneratedValue (strategy = GenerationType.IDENTITY)
      @Column(name = "id")
      private Integer id; // идентификация изображения

      @Lob
      @Type (type = "binary")
      @Column(name = "data_image")
      private byte[] data; // тип данных изображения

      @Column(name = "file_size")
      private Long fileSize; // размер изображения

      @Column(name = "media_type")
      private String mediaType; // тип изображения


}
