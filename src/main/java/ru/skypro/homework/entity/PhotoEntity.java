package ru.skypro.homework.entity;

import lombok.Data;

import javax.persistence.*;

@Entity(name = "photos")
@Data
public class PhotoEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;
    private String filePath;
    private long fileSize;
    private String mediaType;

    @Lob
    private byte[] data;
}
