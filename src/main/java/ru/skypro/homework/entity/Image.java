package ru.skypro.homework.entity;

import lombok.*;
import org.hibernate.annotations.Type;
import javax.persistence.*;


@Entity
@Data
@Table(name = "images")
public class Image {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private Long fileSize;
    private String mediaType;
    @Lob
    @Type(type = "org.hibernate.type.ImageType")
    private byte[] data;

}