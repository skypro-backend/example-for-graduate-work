package ru.skypro.homework.models;

import lombok.Data;

import javax.persistence.*;
@Entity
@Data
public class PhotoEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private  String filePath;
    private  long fileSize;
    private  String mediaType;
    @Lob
    private  byte[] data;
    @OneToOne
    private ItemEntity itemId;
}
