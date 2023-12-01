package ru.skypro.homework.model;

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
    private long fileSize;
    private String mediaType;
    @Lob
    @Type(type = "org.hibernate.type.ImageType")
    private byte[] data;

}
