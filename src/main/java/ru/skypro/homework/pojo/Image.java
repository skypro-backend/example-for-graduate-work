package ru.skypro.homework.pojo;

import lombok.Data;

import javax.persistence.*;

@Entity
@Table(name = "images")
@Data
public class Image {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long imageId;

    @Column(name = "image_path")
    private String imagePath;

    @Column(name = "image_size")
    private Long imageSize;

    @Column(name = "image_type")
    private String imageType;

    @Column
    private byte[] data;


}