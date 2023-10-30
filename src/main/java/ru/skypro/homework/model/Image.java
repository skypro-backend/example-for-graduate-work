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
@Table (name = "image")
public class Image {
      @Id
      @GeneratedValue (strategy = GenerationType.IDENTITY)
      @Column(name = "id")
      private Integer id; // идентификация изображения

      @Lob
      @Type(type="org.hibernate.type.BinaryType")
      @Column(name = "data")
      private byte[] data; // тип данных изображения

      @Column(name = "file_size")
      private Long fileSize; // размер изображения

      @Column(name = "media_type")
      private String mediaType; // тип изображения

}
