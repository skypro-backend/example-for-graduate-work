package ru.skypro.homework.model;

import jakarta.persistence.*;
import lombok.*;
/**
 * Класс описывающий сущность Изображение
 */

@Entity
@Data
public class Image {

    @Id
    @Column(name = "image_id")
    private String id;

    @Lob
    private byte[] image;


}
